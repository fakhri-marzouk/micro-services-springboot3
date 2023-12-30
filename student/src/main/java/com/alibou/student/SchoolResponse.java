package com.alibou.student;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SchoolResponse {
    private Integer id;
    private String name;
    private String email;
}
