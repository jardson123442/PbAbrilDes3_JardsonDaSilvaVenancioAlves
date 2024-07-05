package br.com.jardson.mspayment.service;

import br.com.jardson.mspayment.entity.Payment;
import br.com.jardson.mspayment.entity.User;
import br.com.jardson.mspayment.exceptions.ResourceNotFoundException;
import br.com.jardson.mspayment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentService {

    final PaymentRepository paymentRepository;

    public void save(Payment payment) {
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
