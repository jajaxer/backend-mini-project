package com.interview.order_management_service.service;
import com.interview.order_management_service.model.Customer;
import com.interview.order_management_service.model.Order;
import com.interview.order_management_service.model.OrderRequest;
import com.interview.order_management_service.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private OrderService orderService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateOrder() {
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setProductName("Product A");
        orderRequest.setQuantity(3);
        orderRequest.setTotalAmount(300.00);

        Customer customer = new Customer();
        customer.setEmailAddress("customer@example.com");
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setPhoneNumber("1234567890");

        when(restTemplate.getForObject(anyString(), eq(Customer.class))).thenReturn(customer);

        Order savedOrder = new Order();
        savedOrder.setOrderNumber(UUID.randomUUID());

        when(orderRepository.save(any(Order.class))).thenReturn(savedOrder);

        Order order = orderService.createOrder(orderRequest, "1");
        assertNotNull(order);
    }

    @Test
    public void testGetOrderById() {
        UUID orderId = UUID.randomUUID();
        Order order = new Order();
        order.setOrderNumber(orderId);

        when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));

        Order foundOrder = orderService.getOrderById(orderId);
        assertNotNull(foundOrder);
        assertEquals(orderId, foundOrder.getOrderNumber());
    }

    @Test
    public void testUpdateOrder() {
        UUID orderId = UUID.randomUUID();
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setProductName("Product B");
        orderRequest.setQuantity(5);
        orderRequest.setTotalAmount(500.00);

        Order order = new Order();
        order.setOrderNumber(orderId);
        order.setProductName("Old product name");
        order.setQuantity(1);
        order.setTotalAmount(100.00);

        when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));
        when(orderRepository.save(any(Order.class))).thenReturn(order);

        Order updatedOrder = orderService.updateOrder(orderId, orderRequest);
        assertNotNull(updatedOrder);
        assertEquals(orderRequest.getProductName(), updatedOrder.getProductName());
        assertEquals(orderRequest.getQuantity(), updatedOrder.getQuantity());
        assertEquals(orderRequest.getTotalAmount(), updatedOrder.getTotalAmount());
    }

    @Test
    public void testDeleteOrder() {
        UUID orderId = UUID.randomUUID();

        doNothing().when(orderRepository).deleteById(orderId);

        orderService.deleteOrder(orderId);

        verify(orderRepository, times(1)).deleteById(orderId);
    }
}