package com.interview.order_management_service.model;

import lombok.Data;

@Data
public class OrderRequest {
    private String productName;
    private int quantity;
    private Double totalAmount;
}
