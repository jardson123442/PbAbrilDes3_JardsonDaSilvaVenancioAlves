package br.com.jardson.mspayment.entity;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "rules", url = "http://localhost:8080/v1/customers")
public interface CustomerEndpoint {

    @GetMapping
    CustomerPayment getCustomer();
}
