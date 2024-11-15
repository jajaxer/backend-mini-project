package com.interview.order_management_service.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
    public class OrderRequest {
    @NotBlank(message = "Product name cannot be blank")
    private String productName;
    @NotBlank(message = "Quantity cannot be blank")
    private int quantity;
    @NotBlank(message = "Total Amount cannot be blank")
    private Double totalAmount;
}
