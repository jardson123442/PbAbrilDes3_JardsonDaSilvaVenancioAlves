package br.com.jardson.mspayment.service.infra;

import br.com.jardson.mspayment.entity.Calculate;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "ms-calculate", url = "http://localhost:8080/v1/calculate")
public interface CalculatePaymentService {

    @PostMapping
    Calculate calculatePoints(@RequestBody Calculate request);
}
