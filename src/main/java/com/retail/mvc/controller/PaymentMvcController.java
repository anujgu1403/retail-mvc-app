package com.retail.mvc.controller;

import com.retail.mvc.model.Payment;
import com.retail.mvc.model.Method;
import com.retail.mvc.client.PaymentClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentMvcController {

    private final PaymentClient paymentClient;

    @GetMapping
    public String showPaymentForm(Model model) {
        model.addAttribute("payment", new Payment());
        model.addAttribute("methods", Method.values());
        return "payment-form";
    }

    @PostMapping
    public String processPayment(@ModelAttribute Payment payment, Model model) {
        Payment response = paymentClient.processPayment(payment);
        model.addAttribute("paymentResponse", response);
        return "payment-result";
    }
}
