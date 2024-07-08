package br.com.jardson.mspayment.service;

import br.com.jardson.mspayment.config.PaymentMq;
import br.com.jardson.mspayment.entity.Payment;
import br.com.jardson.mspayment.entity.User;
import br.com.jardson.mspayment.exceptions.ResourceNotFoundException;
import br.com.jardson.mspayment.repository.PaymentRepository;
import br.com.jardson.mspayment.web.response.PaymentResponseDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;


@Service
public class PaymentService {

    @Autowired
    PaymentRepository paymentRepository;

    @Autowired
    PaymentMq paymentMq;

    public void save(Payment payment) throws JsonProcessingException {
        if (payment.getCustomerId() == null || paymentRepository.existsById(payment.getId()) ) {
            throw new ResourceNotFoundException("Customer not found");
        }
        PaymentResponseDto responseDto = new PaymentResponseDto();
            responseDto.setCustomerId(payment.getCustomerId());
            responseDto.setPoints(payment.getTotal());
            payment.setCreatedDate(LocalDateTime.now());
            paymentMq.integration(responseDto);
            paymentRepository.save(payment);
    }

    public List<Payment> findAllPaymentsByCustomerId(Long customerId) {
        return paymentRepository.findByCustomerId(customerId);
    }
}
