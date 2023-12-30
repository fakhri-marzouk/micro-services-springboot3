package com.fakhri.gatewayauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@EnableDiscoveryClient
public class GatewayAuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayAuthApplication.class, args);
    }

}
