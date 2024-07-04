package br.com.jardson.mspayment.service.payments;

import br.com.jardson.mspayment.entity.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "ms-customer", url = "http://localhost:8081/v1/customers")
public interface CustomerPaymentService {

    @GetMapping(value = "/{id}")
    Customer getCustomerPayment(@PathVariable("id") Long id);
}
