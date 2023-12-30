package com.alibou.gateway.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConf {
    @Bean
    WebClient webClient() {
        return WebClient.builder().build();
    }

}