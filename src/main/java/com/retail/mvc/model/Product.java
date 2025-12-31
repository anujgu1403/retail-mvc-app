package com.retail.mvc.model;

import lombok.*;

import java.time.OffsetDateTime;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    private Integer productId;
    private String name;
    private String description;
    private Double unitPrice;
    private String imageUrl;
    private Integer categoryId;
    private OffsetDateTime createdDate;
}