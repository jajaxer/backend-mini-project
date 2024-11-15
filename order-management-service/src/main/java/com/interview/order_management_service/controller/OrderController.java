package com.interview.order_management_service.controller;

import com.interview.order_management_service.model.Order;
import com.interview.order_management_service.model.OrderRequest;
import com.interview.order_management_service.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/order")
    public Order createOrder(@RequestBody OrderRequest request, @RequestParam String customerId) {
        return orderService.createOrder(request, customerId);
    }
}
