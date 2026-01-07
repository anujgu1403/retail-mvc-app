package com.retail.mvc.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${catalog.service.url}")
    private String catalogBaseUrl;

    @Value("${cart.service.url}")
    private String cartBaseUrl;

    @Value("${customer.service.url}")
    private String customerBaseUrl;

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
    public WebClient customerWebClient() {
        return WebClient.builder()
                .baseUrl(customerBaseUrl)
                .build();
    }
}