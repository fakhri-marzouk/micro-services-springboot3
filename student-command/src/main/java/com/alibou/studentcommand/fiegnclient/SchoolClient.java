package com.alibou.studentcommand.fiegnclient;

import com.alibou.studentcommand.dto.SchoolResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "school-service", url = "http://localhost:8070/api/v1/schools")
public interface SchoolClient {
    @GetMapping("/{school-id}")
    SchoolResponse findSchoolById(@PathVariable("school-id") Integer schoolId);

}
