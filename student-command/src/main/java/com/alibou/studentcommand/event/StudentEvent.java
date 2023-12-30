package com.alibou.studentcommand.event;

import com.alibou.studentcommand.entity.Student;
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
