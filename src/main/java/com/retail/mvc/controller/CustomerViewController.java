package com.retail.mvc.controller;

import com.retail.mvc.client.CustomerClient;
import com.retail.mvc.model.Customer;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.ResponseEntity;

@Controller
public class CustomerViewController {

    private final CustomerClient customerClient;

    public CustomerViewController(CustomerClient customerClient) {
        this.customerClient = customerClient;
    }

    @GetMapping({"/login", "/register", "/auth"})
    public String showAuthPage(Model model, HttpServletRequest request) {
        model.addAttribute("customer", new Customer());

        // Determine which tab should be active based on the URL path
        String path = request.getRequestURI();
        boolean isLoginPage = !path.endsWith("/register");

        model.addAttribute("isLoginPage", isLoginPage);

        return "customer/auth";
    }
    @PostMapping("/register")
    public String register(@ModelAttribute Customer customer, Model model) {
        ResponseEntity<?> response = customerClient.register(customer);

        if (response.getStatusCode().is2xxSuccessful()) {
            model.addAttribute("success", "Registration successful! Please login.");
            return "customer/login";
        } else {
            model.addAttribute("error", "Registration failed: " + response.getBody());
            return "customer/register";
        }
    }

    @PostMapping("/login")
    public String login(@ModelAttribute Customer customer, Model model) {
        ResponseEntity<?> response = customerClient.login(customer);

        if (response.getStatusCode().is2xxSuccessful()) {
            // Assuming login success returns some user data or token
            model.addAttribute("success", "Login successful! Welcome " + customer.getEmail());
            return "home"; // or redirect to dashboard
        } else {
            model.addAttribute("error", "Invalid email or password.");
            return "customer/login";
        }
    }
}