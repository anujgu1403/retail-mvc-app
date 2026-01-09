package com.retail.mvc.controller;

import com.retail.mvc.client.OrderClient;
import com.retail.mvc.model.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class OrderViewController {

    @Autowired
    private OrderClient orderClient;

    @PostMapping("/submitcheckout")
    public String placeOrder(@ModelAttribute Cart cart, Model model) {
        orderClient.submitCheckout(cart);
        model.addAttribute("cart", cart);
        return "cart";
    }

}