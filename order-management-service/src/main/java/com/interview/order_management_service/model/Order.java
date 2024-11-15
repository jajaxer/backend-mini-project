package com.interview.order_management_service.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Data
@Entity
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {

    @Id
    @GeneratedValue
    private UUID orderNumber;
    @Column(nullable = false)
    private String productName;
    @Column(nullable = false)
    private int quantity;
    @Column(nullable = false)
    private Double totalAmount;
    @Column(nullable = false)
    private String customerName;
    @Column(nullable = false)
    private String customerEmail;
    @Column()
    private String phoneNumber;
}
