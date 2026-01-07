package com.retail.mvc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${catalog.service.url}")
    private String catalogBaseUrl;

    @Value("${cart.service.url}")
    private String cartBaseUrl;

    @Bean
    public WebClient getCatalogWebClient() {
        return WebClient.builder()
                .baseUrl(catalogBaseUrl)
                .build();
    }

    @Bean
    public WebClient getCartWebClient() {
        return WebClient.builder()
                .baseUrl(cartBaseUrl)
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