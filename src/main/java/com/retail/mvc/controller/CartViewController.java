package com.retail.mvc.controller;

import com.retail.mvc.client.CartClient;
import com.retail.mvc.model.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cart")
public class CartViewController {

    @Autowired
    private CartClient cartClient;

    @GetMapping("/user/{userId}")
    public String viewCart(@PathVariable Long userId, Model model) {
        Cart cart = cartClient.getCartByUserId(userId);
        model.addAttribute("cart", cart);
        return "cart";
    }

    @PostMapping("/add")
    public String addItem(@ModelAttribute Cart cart) {
        cartClient.addItem(cart);
        return "redirect:/cart/user/" + cart.getUserId();
    }

    @PostMapping("/{cartId}/item/{itemId}/delete")
    public String deleteItem(@PathVariable Long cartId,
                             @PathVariable Long itemId,
                             @RequestParam Long userId) {
        cartClient.deleteItem(cartId, itemId);
        return "redirect:/cart/user/" + userId;
    }

    @PostMapping("/{cartId}/item/{itemId}/update")
    public String updateQuantity(@PathVariable Long cartId,
                                 @PathVariable Long itemId,
                                 @RequestParam int delta,
                                 @RequestParam Long userId) {
        cartClient.updateQuantity(cartId, itemId, delta);
        return "redirect:/cart/user/" + userId;
    }
}
