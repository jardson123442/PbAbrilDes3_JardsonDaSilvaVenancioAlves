package br.com.jardson.mspayment.service.infra;

import br.com.jardson.mspayment.entity.Calculate;
import br.com.jardson.mspayment.entity.Rules;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "ms-calculate", url = "http://localhost:8080/v1")
public interface CalculatePaymentService {

    @PostMapping("/calculate")
    Calculate calculatePoints(@RequestBody Calculate request);

    @GetMapping("/rules/{id}")
    Rules getRulesById(@PathVariable("id") Long id);
}
