package com.alibou.student;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class StudentDTO {
    private Integer id;
    private String firstname;
    private String lastname;
    private String email;
    private Integer schoolId;
}
