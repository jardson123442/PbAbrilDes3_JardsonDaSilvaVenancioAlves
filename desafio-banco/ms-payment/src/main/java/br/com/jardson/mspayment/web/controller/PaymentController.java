package br.com.jardson.mspayment.web.controller;

import br.com.jardson.mspayment.entity.Calculate;
import br.com.jardson.mspayment.entity.Customer;
import br.com.jardson.mspayment.entity.Payment;
import br.com.jardson.mspayment.entity.Rules;
import br.com.jardson.mspayment.service.CalculatePaymentService;
import br.com.jardson.mspayment.service.CustomerPaymentService;
import br.com.jardson.mspayment.service.PaymentService;
import br.com.jardson.mspayment.service.RulesPaymentService;
import br.com.jardson.mspayment.web.dto.PaymentDto;
import br.com.jardson.mspayment.web.response.PaymentResponseDto;
import br.com.jardson.mspayment.web.response.TotalResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/payments")
public class PaymentController {

    @Autowired
    public RulesPaymentService rulesPaymentService;
    @Autowired
    public CustomerPaymentService customerPaymentService;
    @Autowired
    public CalculatePaymentService calculatePaymentService;
    @Autowired
    public PaymentService paymentService;


    @PostMapping
    public ResponseEntity<PaymentResponseDto> create(@RequestBody PaymentDto paymentDto) {
        Customer customer = customerPaymentService.getCustomerPayment(paymentDto.getCustomerId());
        customer.setPoints(0);
        Rules rules = rulesPaymentService.getRulesById(paymentDto.getCategoryId());
        Calculate calculate = new Calculate(paymentDto.getCustomerId(), paymentDto.getTotal());
        calculatePaymentService.calculatePoints(calculate);

        Payment payment = new Payment();
        payment.setCategoryId(rules.getId());
        payment.setCustomerId(customer.getId());
        payment.setTotal(customer.getPoints() + (rules.getParity() * paymentDto.getTotal()));

        PaymentResponseDto paymentResponseDto = new PaymentResponseDto();
        paymentResponseDto.setCustomerId(paymentDto.getCustomerId());
        paymentResponseDto.setPoints(payment.getTotal());

        return ResponseEntity.ok(paymentResponseDto);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable(value = "id") Long id) {
        Customer customerPayment = customerPaymentService.getCustomerPayment(id);
        return ResponseEntity.ok(customerPayment);
    }

    @GetMapping(value = "/rules/{id}")
    public ResponseEntity<Rules> getRules(@PathVariable(value = "id") Long id) {
        Rules rules = rulesPaymentService.getRulesById(id);
        return ResponseEntity.ok(rules);
    }

//    @PostMapping
//    public ResponseEntity<TotalResponseDto> calculatePoints(@RequestBody Calculate request) {
//        // Validação dos campos obrigatórios
//        if (request.getCategoryId() == null || request.getValue() == null) {
//            throw new IllegalArgumentException("Todos os campos são obrigatórios");
//        }
//        Rules rule = rulesPaymentService.getRulesById(Long.valueOf(request.getCategoryId()));
//        Double parity = rule.getParity() * request.getValue();
//        TotalResponseDto response = new TotalResponseDto();
//        response.setTotal(parity);
//        return ResponseEntity.ok(response);
//    }
}
