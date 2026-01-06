package com.retail.mvc.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    @Qualifier("catalogWebClient")
    public WebClient catalogWebClient() {
        return WebClient.builder()
                .baseUrl("http://localhost:8081/api/catalog")
                .build();
    }

    @Bean
    @Qualifier("customerWebClient")
    public WebClient customerWebClient() {
        return WebClient.builder()
                .baseUrl("http://localhost:8082/api/customer")  // Adjust to your actual customer service URL
                .build();
    }
}