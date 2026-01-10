package com.retail.mvc.client;

import com.retail.mvc.config.WebClientConfig;
import com.retail.mvc.helper.CardUtilHelper;
import com.retail.mvc.model.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class PaymentClient {

    @Autowired
    private WebClientConfig webClientConfig;

    public Payment processPayment(Payment payment) {

        // Create a JASON with card details and pass payment API
        Map<String, String> payload = new HashMap<>();
        payload.put("payload", CardUtilHelper.toJsonPayload(payment));
        payment.setPayload(payload);

        return webClientConfig.paymentWebClient()
                .post()
                .uri( "/payments")
                .bodyValue(payment)
                .retrieve()
                .bodyToMono(Payment.class)
                .block();
    }
}
