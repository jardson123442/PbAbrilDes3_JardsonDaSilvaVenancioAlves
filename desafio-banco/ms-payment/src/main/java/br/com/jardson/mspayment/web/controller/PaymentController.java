package br.com.jardson.mspayment.web.controller;

import br.com.jardson.mspayment.entity.Customer;
import br.com.jardson.mspayment.entity.CustomerPayment;
import br.com.jardson.mspayment.entity.Payment;
import br.com.jardson.mspayment.entity.Rules;
import br.com.jardson.mspayment.repository.PaymentRepository;
import br.com.jardson.mspayment.service.CalculateService;
import br.com.jardson.mspayment.service.payments.CustomerPaymentService;
import br.com.jardson.mspayment.service.PaymentService;
import br.com.jardson.mspayment.service.payments.RulesPayment;
import br.com.jardson.mspayment.web.dto.PaymentDto;
import br.com.jardson.mspayment.web.response.PaymentResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/payment")
public class PaymentController {

    @Autowired
    public PaymentService paymentService;
    @Autowired
    public RulesPayment rulesPayment;
    @Autowired
    public CustomerPaymentService customerPaymentService;



//    @PostMapping
//    public ResponseEntity<PaymentResponseDto> create(@RequestBody PaymentDto dto) {
//        ResponseEntity<Rules> rules = rulesPayment.getById(dto.getCategoryId());
//        ResponseEntity<Customer> customer = customerPaymentService.getCustomerPayment(dto.getCustomerId());
//
//        CustomerPayment customerPayment = new CustomerPayment();
//        customerPayment.setCustomer(customer.getBody());
//        customerPayment.setCategory(rules.getBody());
//        customerPayment.setPoints(dto.getTotal());
//
//
//        PaymentResponseDto response = new PaymentResponseDto();
//        response.setCustomerId(customerPayment.getCustomer().getId());
//        response.setPoints(customerPayment.getPoints());
//        Payment payment = new Payment();
//        payment.setCustomeriD(customerPayment.getCustomer().getId());
//        payment.setCategory(customerPayment.getCategory().getId());
//        payment.setTotal(dto.getTotal());
//        paymentService.save(payment);
//        return ResponseEntity.ok(response);
//    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Customer>  getCustomer(@PathVariable(value = "id") Long id) {
        Customer customerPayment = customerPaymentService.getCustomerPayment(id);
        return ResponseEntity.ok(customerPayment);
    }


}
