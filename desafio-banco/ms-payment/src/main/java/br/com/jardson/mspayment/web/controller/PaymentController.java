package br.com.jardson.mspayment.web.controller;

import br.com.jardson.mspayment.entity.CustomerPayment;
import br.com.jardson.mspayment.service.CalculateOrderCustomerEndpoint;
import br.com.jardson.mspayment.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/payment")
public class PaymentController {

    public CalculateOrderCustomerEndpoint customerEndpoint;
    public PaymentService paymentService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<CustomerPayment>  getCustomer(@PathVariable(value = "id") Long id) {
        CustomerPayment customerPayment = customerEndpoint.getCustomerPayment(id);
        return ResponseEntity.ok(customerPayment);
    }

    @GetMapping
    public String string() {
        return "HI";
    }
}
