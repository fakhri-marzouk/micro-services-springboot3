package com.alibou.studentcommand;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class StudentCommandApplication {
    public static void main(String[] args) {
        SpringApplication.run(StudentCommandApplication.class, args);
    }

}
