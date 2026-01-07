package com.retail.mvc.model;

import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartSummary {
    private BigDecimal subtotal;
    private BigDecimal tax;
    private BigDecimal grandTotal;
}
