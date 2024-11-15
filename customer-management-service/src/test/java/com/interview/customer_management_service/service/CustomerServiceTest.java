package com.interview.customer_management_service.service;
import com.interview.customer_management_service.model.Customer;
import com.interview.customer_management_service.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createCustomer() {
        Customer customer = new Customer();
        when(customerRepository.save(customer)).thenReturn(customer);

        Customer createdCustomer = customerService.createCustomer(customer);

        assertNotNull(createdCustomer);
        verify(customerRepository, times(1)).save(customer);
    }

    @Test
    void getCustomerById() {
        UUID customerId = UUID.randomUUID();
        Customer customer = new Customer();
        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));

        Optional<Customer> foundCustomer = customerService.getCustomerById(customerId);

        assertTrue(foundCustomer.isPresent());
        assertEquals(customer, foundCustomer.get());
        verify(customerRepository, times(1)).findById(customerId);
    }

    @Test
    void deleteCustomerById() {
        UUID customerId = UUID.randomUUID();
        doNothing().when(customerRepository).deleteById(customerId);

        customerService.deleteCustomerById(customerId);

        verify(customerRepository, times(1)).deleteById(customerId);
    }

    @Test
    void updateCustomer() {
        Customer customer = new Customer();
        when(customerRepository.save(customer)).thenReturn(customer);

        Customer updatedCustomer = customerService.updateCustomer(customer);

        assertNotNull(updatedCustomer);
        verify(customerRepository, times(1)).save(customer);
    }
}