package com.interview.order_management_service.service;

import com.interview.order_management_service.model.Customer;
import com.interview.order_management_service.model.Order;
import com.interview.order_management_service.model.OrderRequest;
import com.interview.order_management_service.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;
import java.util.UUID;

@Service
public class OrderService {


    private final OrderRepository orderRepository;

    private final RestTemplate restTemplate;

    @Autowired
    public OrderService(OrderRepository orderRepository, RestTemplate restTemplate) {
        this.orderRepository = orderRepository;
        this.restTemplate = restTemplate;
    }

    public Order createOrder(OrderRequest request, String customerId) {
        String url  = "http://localhost:8070/customers/" + customerId;
        Customer customer = restTemplate.getForObject(url, Customer.class);
        Order order = Order.builder()
                .phoneNumber(request.getProductName())
                .quantity(request.getQuantity())
                .totalAmount(request.getTotalAmount())
                .customerEmail(customer.getEmail())
                .customerName(customer.getFirstName() + ' ' + customer.getLastName())
                .phoneNumber(customer.getPhoneNumber())
                .build();
        return orderRepository.save(order);
    }

}
