package com.retail.mvc.client;

import com.retail.mvc.config.WebClientConfig;
import com.retail.mvc.model.Category;
import com.retail.mvc.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CatalogClient {

  @Autowired
  private WebClientConfig webClientConfig;


    public List<Product> getAll() {
        return webClientConfig.getCatalogWebClient()
                .get()
                .uri("/products")
                .retrieve()
                .bodyToFlux(Product.class)
                .collectList()
                .block();
    }

    public Product getById(int id) {
        return webClientConfig.getCatalogWebClient()
                .get()
                .uri("/product/{id}", id)
                .retrieve()
                .bodyToMono(Product.class)
                .block();
    }

    public Product add(Product product) {
        return webClientConfig.getCatalogWebClient()
                .post()
                .uri("/product/add")
                .bodyValue(product)
                .retrieve()
                .bodyToMono(Product.class)
                .block();
    }

    public Product update(Product product) {
        return webClientConfig.getCatalogWebClient()
                .patch()
                .uri("/product/update")
                .bodyValue(product)
                .retrieve()
                .bodyToMono(Product.class)
                .block();
    }

    public void delete(int id) {
        webClientConfig.getCatalogWebClient()
                .delete()
                .uri("/product/delete/{id}", id)
                .retrieve()
                .toBodilessEntity()
                .block();
    }
    public List<Category> getAllCategories() {
        return webClientConfig.getCatalogWebClient()
                .get()
                .uri("/categories")
                .retrieve()
                .bodyToFlux(Category.class)
                .collectList()
                .block();
    }

    public List<Product> getProductsByCategory(Integer categoryId) {
        return webClientConfig.getCatalogWebClient()
                .get()
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