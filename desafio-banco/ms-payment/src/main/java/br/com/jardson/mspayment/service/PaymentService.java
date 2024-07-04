package br.com.jardson.mspayment.service;

import br.com.jardson.mspayment.entity.Customer;
import br.com.jardson.mspayment.entity.CustomerPayment;
import br.com.jardson.mspayment.entity.Payment;
import br.com.jardson.mspayment.entity.Rules;
import br.com.jardson.mspayment.repository.PaymentRepository;
import br.com.jardson.mspayment.service.payments.CustomerPaymentService;
import br.com.jardson.mspayment.service.payments.RulesPayment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    public CustomerPaymentService customerPaymentService;
    @Autowired
    private RulesPayment rulesService;
    public PaymentRepository repository;

    public Payment save(Payment payment) {
        return repository.save(payment);
    }
//    public CustomerPayment getCustomer(Long idCustomer, Long idCategory, Double points) {
//        ResponseEntity<Rules> rules = rulesService.getById(idCategory);
//        ResponseEntity<Customer> customer = customerPaymentService.getCustomerById(idCustomer);
//
//
//        CustomerPayment customerPayment = new CustomerPayment();
//        customerPayment.setCustomer(customer.getBody());
//        customerPayment.setCategory(rules.getBody());
//        customerPayment.setPoints(points);
//
//        return CustomerPayment.builder()
//                .customer(customer.getBody())
//                .category(rules.getBody())
//                .points(points)
//                .build();
//    }
}
