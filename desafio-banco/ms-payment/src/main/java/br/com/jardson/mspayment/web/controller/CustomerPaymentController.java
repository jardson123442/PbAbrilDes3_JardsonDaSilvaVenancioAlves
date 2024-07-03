package br.com.jardson.mspayment.web.controller;

import br.com.jardson.mspayment.entity.CustomerEndpoint;
import br.com.jardson.mspayment.entity.CustomerPayment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/payment/user")
public class CustomerPaymentController {

    @Autowired
    CustomerEndpoint customerEndpoint;

    @GetMapping
    public CustomerPayment getCustomerPayment() {
        return customerEndpoint.getCustomer();
    }
}
