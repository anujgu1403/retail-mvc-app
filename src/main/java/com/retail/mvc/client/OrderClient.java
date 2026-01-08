package com.retail.mvc.client;

import com.retail.mvc.config.WebClientConfig;
import com.retail.mvc.model.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderClient {

    @Autowired
    private WebClientConfig webClientConfig;

    public Cart submitCheckout(Cart cart) {
        return webClientConfig.orderWebClient()
                .post()
                .uri( "/submitcheckout")
                .bodyValue(cart)
                .retrieve()
                .bodyToMono(Cart.class)
                .block();
    }
}
