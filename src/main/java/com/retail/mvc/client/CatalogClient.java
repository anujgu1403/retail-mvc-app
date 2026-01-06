package com.retail.mvc.client;

import com.retail.mvc.model.Category;
import com.retail.mvc.model.Product;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Component
public class CatalogClient {

    private final WebClient webClient;

    public CatalogClient(@Qualifier("catalogWebClient") WebClient webClient) {
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
    public List<Category> getAllCategories() {
        return webClient.get()
                .uri("/categories")
                .retrieve()
                .bodyToFlux(Category.class)
                .collectList()
                .block();
    }

    public List<Product> getProductsByCategory(Integer categoryId) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/products")
                        .queryParam("categoryId", categoryId)
                        .build())
                .retrieve()
                .bodyToFlux(Product.class)
                .collectList()
                .block();
    }
}