package com.retail.mvc.controller;

import com.retail.mvc.client.CustomerClient;
import com.retail.mvc.model.Customer;
import com.retail.mvc.model.LoginResponseDTO;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
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
        System.out.println(response);

        if (response.getStatusCode().is2xxSuccessful()) {
            model.addAttribute("success", "Registration successful! Please login.");
            model.addAttribute("isLoginPage", true);
        } else {
            model.addAttribute("error", "Registration failed: " + response.getBody());
            model.addAttribute("isLoginPage", false);
        }
            return "customer/auth";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute Customer customer, HttpServletResponse response, Model model) {
        ResponseEntity<LoginResponseDTO> apiResponse = customerClient.login(customer);
        System.out.println(apiResponse);
        System.out.println(apiResponse.getStatusCode());
        System.out.println(apiResponse.getBody());

        if (apiResponse.getStatusCode().is2xxSuccessful()) {
            String token = (String) apiResponse.getBody().getToken();
            System.out.println(apiResponse.getBody().getToken());


            if (token != null) {
                ResponseCookie jwtCookie = ResponseCookie.from("jwt", token)
                        .httpOnly(true)                  // ← protects from XSS
                        .secure(true)                    // ← only HTTPS (use false only for local dev!)
                        .path("/")                       // whole application
                        .maxAge(15 * 60)                 // 15 minutes – match your token expiry
                        .sameSite("Strict")              // ← or "Lax" or "None" (with Secure=true!)
                        // .domain("yourdomain.com")     // optional
                        .build();

                // This is how you actually send it
                response.addHeader(HttpHeaders.SET_COOKIE, jwtCookie.toString());

                model.addAttribute("success", "Login successful! Welcome " + customer.getEmail());
                return "redirect:/products";
            }
        }

        model.addAttribute("error", "Invalid email or password.");
        return "customer/login";
    }
}