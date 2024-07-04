package br.com.jardson.mspayment.web.controller;

import br.com.jardson.mspayment.entity.CustomerPayment;
import br.com.jardson.mspayment.service.CalculateService;
import br.com.jardson.mspayment.service.payments.CustomerPaymentService;
import br.com.jardson.mspayment.service.PaymentService;
import br.com.jardson.mspayment.web.dto.PaymentDto;
import br.com.jardson.mspayment.web.response.PaymentResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/payment")
public class PaymentController {

    public CustomerPaymentService customerEndpoint;
    public PaymentService paymentService;
    public CalculateService calculateService;

    @PostMapping
    public ResponseEntity<PaymentResponseDto> create(@RequestBody PaymentDto dto) {
        CustomerPayment customerPayment = calculateService.getCalculateCustomerPayment(
                dto.getCustomerId(),
                dto.getCategoryId(),
                dto.getTotal()
        );

        PaymentResponseDto response = new PaymentResponseDto();
        response.setCustomerId(customerPayment.getCustomer().getCustomerId());
        response.setPoints(customerPayment.getPoints());

        return ResponseEntity.ok(response);
    }

}
