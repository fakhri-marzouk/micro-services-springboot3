package com.alibou.gateway.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")

public class Keycloak {
    private static final String KEYCLOAK_TOKEN_URL = "http://localhost:9090/realms/dive-into/protocol/openid-connect/token";
    @Value("${realm}")
    private String realm;

    @Value("${client-id}")
    private String clientId;

    @Value("${grant-type}")
    private String grantType;

    @Value("${client-secret}")
    private String clientSecret;
    private final WebClient webClient;

    public Keycloak(WebClient webClient) {
        this.webClient = webClient;
    }

    @PostMapping("")
    public Mono<ResponseEntity<String>> login(@RequestParam String username, @RequestParam String password) {

        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("grant_type",grantType);
        formData.add("username", username);
        formData.add("password", password);
        formData.add("client_id", clientId);
        formData.add("client_secret",clientSecret);

        // Set the response type to String to directly receive the JSON response
        return webClient.post()
                .uri("http://keycloak:8080/realms/dive-into/protocol/openid-connect/token")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(BodyInserters.fromFormData(formData))
                .exchange()
                .flatMap(clientResponse -> clientResponse.toEntity(String.class))
                .doOnError(error -> {
                    // Log or handle the error
                    error.printStackTrace();
                })
                .doOnSuccess(responseEntity -> {
                    System.out.println("Received response: " + responseEntity.getBody());
                });
    }
}
