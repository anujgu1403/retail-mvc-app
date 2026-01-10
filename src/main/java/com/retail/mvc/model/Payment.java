package com.retail.mvc.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Map;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(Include.NON_NULL)
public class Payment {

    private Method method;
    private BigDecimal amount;
    private String currency;
    private Map<String, String> payload;
    private Long paymentId;
    private String status;
    private String message;
    private OffsetDateTime processedAt;
    private CardDetails cardDetails;

}