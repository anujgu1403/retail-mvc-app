package com.retail.mvc.helper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.retail.mvc.model.Payment;

public class CardUtilHelper {

    public static String toJsonPayload(Payment payment) {
        try {
            return new ObjectMapper().writeValueAsString(payment.getCardDetails());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
