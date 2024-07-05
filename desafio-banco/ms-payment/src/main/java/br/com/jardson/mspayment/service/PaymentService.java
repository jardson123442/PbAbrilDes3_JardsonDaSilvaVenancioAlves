package br.com.jardson.mspayment.service;

import br.com.jardson.mspayment.entity.Payment;
import br.com.jardson.mspayment.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    PaymentRepository paymentRepository;

    public void save(Payment payment) {
        paymentRepository.save(payment);
    }
}
