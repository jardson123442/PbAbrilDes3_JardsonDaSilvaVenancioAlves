package br.com.jardson.mspayment.service.payments;

import br.com.jardson.mspayment.entity.Rules;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "ms-calculate", url = "http://localhost:8082/v1/rules")
public interface RulesPayment {

    @GetMapping("/{id}")
    ResponseEntity<Rules> getById(@PathVariable Long id);


}
