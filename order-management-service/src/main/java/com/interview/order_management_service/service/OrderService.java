package com.interview.order_management_service.service;

import com.interview.order_management_service.model.Customer;
import com.interview.order_management_service.model.Order;
import com.interview.order_management_service.model.OrderRequest;
import com.interview.order_management_service.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import java.util.List;
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
                .productName(request.getProductName())
                .quantity(request.getQuantity())
                .totalAmount(request.getTotalAmount())
                .customerEmail(customer.getEmailAddress())
                .customerName(customer.getFirstName() + ' ' + customer.getLastName())
                .phoneNumber(customer.getPhoneNumber())
                .build();
        return orderRepository.save(order);
    }
    public  Order getOrderById(UUID orderId){
        return  orderRepository.findById(orderId).get();
    }

    public Order updateOrder(UUID orderId, OrderRequest request) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if(optionalOrder.isPresent()){
            Order order = optionalOrder.get();
            order.setProductName(request.getProductName());
            order.setQuantity(request.getQuantity());
            order.setTotalAmount(request.getTotalAmount());
            return orderRepository.save(order);
        } else {
            return null;
        }
    }

    public void deleteOrder(UUID orderId) {
        orderRepository.deleteById(orderId);
    }

}
