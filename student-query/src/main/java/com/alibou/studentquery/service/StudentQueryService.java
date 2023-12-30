package com.alibou.studentquery.service;

import com.alibou.studentquery.event.StudentEvent;
import com.alibou.studentquery.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StudentQueryService {

    private final StudentRepository studentRepository;

    @KafkaListener(topics = "student-topic")
    public void processStudent(StudentEvent studentEvent) {
        studentRepository.save(studentEvent.getStudent());
    }
}
