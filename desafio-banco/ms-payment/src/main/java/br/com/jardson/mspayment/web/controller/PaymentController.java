package br.com.jardson.mspayment.web.controller;

import br.com.jardson.mspayment.entity.Calculate;
import br.com.jardson.mspayment.entity.Customer;
import br.com.jardson.mspayment.entity.Payment;
import br.com.jardson.mspayment.entity.Rules;
import br.com.jardson.mspayment.service.infra.CalculatePaymentService;
import br.com.jardson.mspayment.service.infra.CustomerPaymentService;
import br.com.jardson.mspayment.service.PaymentService;
import br.com.jardson.mspayment.web.dto.PaymentDto;
import br.com.jardson.mspayment.web.response.PaymentResponseDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/payments")
@RequiredArgsConstructor
public class PaymentController {

    public final CustomerPaymentService customerPaymentService;

    public final CalculatePaymentService calculatePaymentService;

    public final PaymentService paymentService;


    @PostMapping
    public ResponseEntity<PaymentResponseDto> create(@RequestBody PaymentDto paymentDto) throws JsonProcessingException {
        Customer customer = customerPaymentService.getCustomerPayment(paymentDto.getCustomerId());
        customer.setPoints(0);
        Rules rules = calculatePaymentService.getRulesById(paymentDto.getCategoryId());
        Calculate calculate = new Calculate(paymentDto.getCustomerId(), paymentDto.getTotal());
        calculatePaymentService.calculatePoints(calculate);

        Payment payment = new Payment();
        payment.setId(UUID.randomUUID().toString());
        payment.setCategoryId(paymentDto.getCategoryId());
        payment.setCustomerId(paymentDto.getCustomerId());
        payment.setTotal(customer.getPoints() + (rules.getParity() * paymentDto.getTotal()));
        payment.setCreatedDate(LocalDateTime.now());
        paymentService.save(payment);

        PaymentResponseDto paymentResponseDto = new PaymentResponseDto();
        paymentResponseDto.setCustomerId(paymentDto.getCustomerId());
        paymentResponseDto.setPoints(payment.getTotal());

        return ResponseEntity.ok(paymentResponseDto);
    }

    @GetMapping(value = "/user/{id}")
    public List<Payment> getAllPaymentsByCustomerId(@PathVariable Long id) {
        return paymentService.findAllPaymentsByCustomerId(id);
    }

//    @GetMapping(value = "/user/{id}")
//    public ResponseEntity<Customer> getCustomer(@PathVariable(value = "id") Long id) {
//        Customer customerPayments = paymentService.findById(id);
//        return ResponseEntity.ok(customerPayment);
//    }


}
