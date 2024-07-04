package br.com.jardson.mspayment.service.payments;

import br.com.jardson.mspayment.entity.Calculate;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "ms-calculate", url = "http://localhost:8082/v1/calculate")
public interface CalculatePayment {

    @GetMapping(value = "/{id}")
    Calculate calculatePoints(@PathVariable("id") Long id);
}
