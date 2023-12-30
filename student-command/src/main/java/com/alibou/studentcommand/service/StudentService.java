package com.alibou.studentcommand.service;

import com.alibou.studentcommand.dto.SchoolResponse;
import com.alibou.studentcommand.dto.StudentDTO;
import com.alibou.studentcommand.entity.Student;
import com.alibou.studentcommand.event.StudentEvent;
import com.alibou.studentcommand.fiegnclient.SchoolClient;
import com.alibou.studentcommand.repository.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository repository;
    private final SchoolClient schoolClient;
    private final KafkaTemplate<String, StudentEvent> kafkaTemplate;
    public void saveStudent(StudentEvent student) {
        SchoolResponse schoolResponse = schoolClient.findSchoolById(student.getStudent().getSchoolId());
        if (schoolResponse.getId() != 0) {
            Student save = repository.save(student.getStudent());
            StudentEvent event = new StudentEvent("create", save);
            kafkaTemplate.send("student-topic", event);
        } else {
            // Handle other exceptions
            System.out.println("Error occurred while saving student");
            // You can choose to log the exception or throw a custom exception, based on your needs
        }
    }

    public List<Student> findAllStudents() {
        return repository.findAll();
    }

    public String deleteStudent(Integer id) {
        repository.deleteById(id);
        return "Student was deleted successfully";
    }

    public String updateStudent(@RequestBody StudentDTO studentDTO) {

        Student student = repository.findById(studentDTO.getId()).orElseThrow(
                (() -> new EntityNotFoundException("Student not found with ID: " + studentDTO.getId())));
        student.setFirstname(studentDTO.getFirstname());
        student.setLastname(studentDTO.getLastname());
        student.setEmail(studentDTO.getEmail());
        SchoolResponse schoolResponse = schoolClient.findSchoolById(studentDTO.getSchoolId());
        student.setSchoolId(schoolResponse.getId());
        Student save = repository.save(student);
        StudentEvent event = new StudentEvent("update", save);
        kafkaTemplate.send("student-topic", event);
        return "Student updated successfully";
    }

    public List<Student> findAllStudentsBySchool(Integer schoolId) {
        return repository.findAllBySchoolId(schoolId);
    }
}
