package com.retail.mvc.client;

import com.retail.mvc.model.Customer;
import com.retail.mvc.model.LoginResponseDTO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class CustomerClient {

    private final WebClient webClient;

    public CustomerClient(@Qualifier("customerWebClient") WebClient webClient) {
        this.webClient = webClient;
    }

    public ResponseEntity<?> register(Customer customer) {
        return webClient.post()
                .uri("/register")
                .bodyValue(customer)
                .retrieve()
                .toEntity(Object.class)  // ResponseEntity<?> returns Object body
                .block();
    }

    public ResponseEntity<LoginResponseDTO> login(Customer customer) {
        return webClient.post()
                .uri("/login")
                .bodyValue(customer)
                .retrieve()
                .toEntity(LoginResponseDTO.class)
                .block();
    }
}