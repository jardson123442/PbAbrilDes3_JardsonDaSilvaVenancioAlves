package br.com.jardson.mspayment.web;

import br.com.jardson.mspayment.config.PaymentMq;
import br.com.jardson.mspayment.entity.Payment;
import br.com.jardson.mspayment.exceptions.ResourceNotFoundException;
import br.com.jardson.mspayment.repository.PaymentRepository;
import br.com.jardson.mspayment.service.PaymentService;
import br.com.jardson.mspayment.web.response.PaymentResponseDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceTest {

    @Mock
    private PaymentRepository paymentRepository;

    @Mock
    private PaymentMq paymentMq;

    @InjectMocks
    private PaymentService paymentService;

    private Payment payment;

    @BeforeEach
    public void setUp() {
        payment = new Payment();
        payment.setId(UUID.randomUUID().toString());
        payment.setCustomerId(1L);
        payment.setTotal(100);
        payment.setCategoryId(1L);
        payment.setCreatedDate(LocalDateTime.now());
    }


    @Test
    public void testSavePaymentSuccess() throws JsonProcessingException {
        when(paymentRepository.existsById(payment.getId())).thenReturn(false);

        paymentService.save(payment);

        assertNotNull(payment.getCreatedDate());
        verify(paymentRepository, times(1)).save(payment);
        verify(paymentMq, times(1)).integration(any(PaymentResponseDto.class));
    }

    @Test
    public void testSavePaymentThrowsResourceNotFoundExceptionForNullCustomerId() {
        payment.setCustomerId(null);

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            paymentService.save(payment);
        });


        assertEquals("Customer not found", exception.getMessage());
    }

    @Test
    public void testSavePaymentThrowsResourceNotFoundExceptionForExistingPaymentId() {
        when(paymentRepository.existsById(payment.getId())).thenReturn(true);

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            paymentService.save(payment);
        });


        assertEquals("Customer not found", exception.getMessage());
    }

    @Test
    public void testFindAllPaymentsByCustomerIdReturnsPayments() {
        // Simulate finding payments for customer 1L
        List<Payment> mockPayments = new ArrayList<>();
        mockPayments.add(payment);
        when(paymentRepository.findByCustomerId(1L)).thenReturn(mockPayments);

        List<Payment> foundPayments = paymentService.findAllPaymentsByCustomerId(1L);

        assertEquals(1, foundPayments.size());
        assertEquals(payment.getId(), foundPayments.get(0).getId());
        assertEquals(payment.getCustomerId(), foundPayments.get(0).getCustomerId());
        // Add more assertions as needed
    }

    @Test
    public void testFindAllPaymentsByCustomerIdReturnsEmptyList() {
        // Simulate no payments found for customer 2L
        when(paymentRepository.findByCustomerId(2L)).thenReturn(new ArrayList<>());

        List<Payment> foundPayments = paymentService.findAllPaymentsByCustomerId(2L);

        assertEquals(0, foundPayments.size());
    }
}

