package br.com.jardson.mspayment.service;

import br.com.jardson.mspayment.entity.Customer;
import br.com.jardson.mspayment.entity.CustomerPayment;
import br.com.jardson.mspayment.entity.Rules;
import br.com.jardson.mspayment.service.payments.CustomerPaymentService;
import br.com.jardson.mspayment.service.payments.RulesPayment;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CalculateService {

    public final CustomerPaymentService customerPaymentService;
    private final RulesPayment rulesService;

    public CustomerPayment getCalculateCustomerPayment(Long idCustomer, Long idCategory, Double points) {
        ResponseEntity<Rules> rules = rulesService.getById(idCategory);
        ResponseEntity<Customer> customer = customerPaymentService.getCustomerById(idCustomer);

        CustomerPayment customerPayment = new CustomerPayment();
        customerPayment.setCustomer(customer.getBody());
        customerPayment.setCategory(rules.getBody());
        customerPayment.setPoints(points);

        return customerPayment;
    }
}
