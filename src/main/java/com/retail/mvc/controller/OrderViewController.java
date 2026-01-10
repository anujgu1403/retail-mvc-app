package com.retail.mvc.controller;

import com.retail.mvc.client.OrderClient;
import com.retail.mvc.model.Cart;
import com.retail.mvc.model.CartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;

@Controller
public class OrderViewController {

    @Autowired
    private OrderClient orderClient;

    @PostMapping("/checkout")
    public String placeOrder(@ModelAttribute Cart cart, Model model) {
        orderClient.submitCheckout(cart);
        model.addAttribute("submittedCart", cart);
        return "order-summary";
    }


    @GetMapping("/checkout")
    public String showCartForm(Model model) {

        Cart cart = new Cart();
        cart.setCartItems(new ArrayList<>());

        // Add 2 empty rows by default (can be dynamic later)
        cart.getCartItems().add(new CartItem());
        cart.getCartItems().add(new CartItem());

        model.addAttribute("cart", cart);
        return "order-form";
    }

}