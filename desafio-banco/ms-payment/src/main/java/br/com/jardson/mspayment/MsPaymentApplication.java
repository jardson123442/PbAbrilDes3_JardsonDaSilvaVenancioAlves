package br.com.jardson.mspayment;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;

@EnableRabbit
@SpringBootApplication
@EnableFeignClients
public class MsPaymentApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsPaymentApplication.class, args);
    }
}
