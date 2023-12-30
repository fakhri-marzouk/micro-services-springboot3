package com.alibou.studentcommand.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="student_command")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstname;
    private String lastname;
    private String email;
    private Integer schoolId;
}
