package com.retail.mvc.client;

import com.retail.mvc.model.Product;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Component
public class CatalogClient {

    private final WebClient webClient;

    public CatalogClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public List<Product> getAll() {
        return webClient.get()
                .uri("/products")
                .retrieve()
                .bodyToFlux(Product.class)
                .collectList()
                .block();
    }

    public Product getById(int id) {
        return webClient.get()
                .uri("/product/{id}", id)
                .retrieve()
                .bodyToMono(Product.class)
                .block();
    }

    public Product add(Product product) {
        return webClient.post()
                .uri("/product/add")
                .bodyValue(product)
                .retrieve()
                .bodyToMono(Product.class)
                .block();
    }

    public Product update(Product product) {
        return webClient.patch()
                .uri("/product/update")
                .bodyValue(product)
                .retrieve()
                .bodyToMono(Product.class)
                .block();
    }

    public void delete(int id) {
        webClient.delete()
                .uri("/product/delete/{id}", id)
                .retrieve()
                .toBodilessEntity()
                .block();
    }
}