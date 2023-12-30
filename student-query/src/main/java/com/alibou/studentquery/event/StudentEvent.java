package com.alibou.studentquery.event;

import com.alibou.studentquery.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentEvent {
    private String eventType;
    private Student student;
}
