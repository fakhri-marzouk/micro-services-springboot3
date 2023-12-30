package com.alibou.student;

import com.alibou.student.event.PlaceStudentEvent;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository repository;
    private final SchoolClient schoolClient;

    private final KafkaTemplate<String,PlaceStudentEvent> kafkaTemplate;

    public void saveStudent(Student student) {
        SchoolResponse schoolResponse =schoolClient.findSchoolById(student.getSchoolId());

        if (schoolResponse.getId()!=0){
            repository.save(student);
            kafkaTemplate.send("notificationTopic", new PlaceStudentEvent(student.getId()));
        }else  {
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
        SchoolResponse schoolResponse=schoolClient.findSchoolById(studentDTO.getSchoolId());
        student.setSchoolId(schoolResponse.getId());
        repository.save(student);
        return "Student updated successfully";

    }

    public List<Student> findAllStudentsBySchool(Integer schoolId) {
        return repository.findAllBySchoolId(schoolId);
    }
}
