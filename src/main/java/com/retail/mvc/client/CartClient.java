package com.retail.mvc.client;

import com.retail.mvc.config.WebClientConfig;
import com.retail.mvc.model.Cart;
import com.retail.mvc.model.CartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class CartClient {

    @Autowired
    private WebClientConfig webClientConfig;

    public Cart getCartByUserId(Long userId) {
        return webClientConfig.getCartWebClient()
                .get()
                .uri( "/user/{userId}", userId)
                .retrieve()
                .bodyToMono(Cart.class)
                .block();
    }

    public Cart getCartByCartId(Long cartId) {
        return webClientConfig.getCartWebClient()
                .get()
                .uri("/{cartId}", cartId)
                .retrieve()
                .bodyToMono(Cart.class)
                .block();
    }

    public List<CartItem> getCartItems(Long cartId) {
        return webClientConfig.getCartWebClient()
                .get()
                .uri( "/{cartId}/items", cartId)
                .retrieve()
                .bodyToFlux(CartItem.class)
                .collectList()
                .block();
    }

    public Cart addItem(Cart cart) {
        return webClientConfig.getCartWebClient()
                .post()
                .uri( "/add")
                .bodyValue(cart)
                .retrieve()
                .bodyToMono(Cart.class)
                .block();
    }

    public void deleteItem(Long cartId, Long cartItemId) {
        webClientConfig.getCartWebClient()
                .delete()
                .uri("/{cartId}/items/{itemId}", cartId, cartItemId)
                .retrieve()
                .toBodilessEntity()
                .block();
    }

    public void updateQuantity(Long cartId, Long cartItemId, int delta) {
        webClientConfig.getCartWebClient()
                .put()
                .uri(uriBuilder ->
                        uriBuilder
                                .path("/{cartId}/items/{itemId}/quantity")
                                .queryParam("delta", delta)
                                .build(cartId, cartItemId))
                .retrieve()
                .toBodilessEntity()
                .block();
    }
}
