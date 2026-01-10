package com.retail.mvc.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CardDetails {
    private String cardNumber;
    private String expiryMonth;
    private String expiryYear;
    private String cvv;
    private String pin;
}
