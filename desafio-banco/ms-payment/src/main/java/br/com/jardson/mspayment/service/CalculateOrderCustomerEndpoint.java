package br.com.jardson.mspayment.service;

import br.com.jardson.mspayment.entity.CustomerPayment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "rules", url = "http://localhost:8081/v1/customers")
public interface CalculateOrderCustomerEndpoint {

    @GetMapping(value = "/{id}")
    CustomerPayment getCustomerPayment(@PathVariable("id") Long id);
}
