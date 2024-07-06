package br.com.jardson.mspayment.config;


import br.com.jardson.mspayment.entity.Payment;
import br.com.jardson.mspayment.web.response.PaymentResponseDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentMq {

    private final AmqpTemplate amqpTemplate;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public void integration(PaymentResponseDto payment) throws JsonProcessingException {
        amqpTemplate.convertAndSend(
                "customer-points-exchanges",
                "customer-points-rout-key",
                objectMapper.writeValueAsString(payment)
        );
    }

}
