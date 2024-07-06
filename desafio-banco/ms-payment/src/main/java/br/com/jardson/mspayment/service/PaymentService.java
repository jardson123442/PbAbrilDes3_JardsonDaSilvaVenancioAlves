package br.com.jardson.mspayment.service;

import br.com.jardson.mspayment.config.PaymentMq;
import br.com.jardson.mspayment.entity.Payment;
import br.com.jardson.mspayment.entity.User;
import br.com.jardson.mspayment.exceptions.ResourceNotFoundException;
import br.com.jardson.mspayment.repository.PaymentRepository;
import br.com.jardson.mspayment.web.response.PaymentResponseDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor
public class PaymentService {

    final PaymentRepository paymentRepository;
    final PaymentMq paymentMq;

    public void save(Payment payment) throws JsonProcessingException {
        PaymentResponseDto responseDto = new PaymentResponseDto();
        responseDto.setCustomerId(payment.getCustomerId());
        responseDto.setPoints(payment.getTotal());
        payment.setCreatedDate(LocalDateTime.now());
        paymentMq.integration(responseDto);
        paymentRepository.save(payment);
    }

    public Payment findById(String id) {
        return paymentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
    }

    public List<Payment> findAllPayments() {
        return paymentRepository.findAll();
    }

    public List<Payment> findAllPaymentsByCustomerId(Long customerId) {
        return paymentRepository.findByCustomerId(customerId);
    }
}
