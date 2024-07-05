package br.com.jardson.mspayment.service.infra;

import br.com.jardson.mspayment.entity.Rules;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "ms-calculate", url = "http://localhost:8080/v1/rules")
public interface RulesPaymentService {

    @GetMapping("/{id}")
    Rules getRulesById(@PathVariable("id") Long id);


}
