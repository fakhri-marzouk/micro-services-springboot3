package com.alibou.student;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
@RequiredArgsConstructor

public class StudentController {

    private final StudentService service;

    @PostMapping
    public ResponseEntity<String> save(@RequestBody Student student) {
        try {
            service.saveStudent(student);
            return ResponseEntity.ok("Student saved successfully");
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving student");
        }
    }

    @GetMapping
    public ResponseEntity<List<Student>> findAllStudents() {
        return ResponseEntity.ok(service.findAllStudents());
    }

    @GetMapping("/school/{school-id}")
    public ResponseEntity<List<Student>> findAllStudents(@PathVariable("school-id") Integer schoolId)
    {
        return ResponseEntity.ok(service.findAllStudentsBySchool(schoolId));
    }


    @PutMapping
    public String updateStudent(@RequestBody StudentDTO studentDTO){
       return service.updateStudent(studentDTO);
    }
}
