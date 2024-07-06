package br.com.jardson.mscustomer.config;

import br.com.jardson.mscustomer.entity.Customer;
import br.com.jardson.mscustomer.repository.CustomerRepository;
import br.com.jardson.mscustomer.web.dto.CustomerMQ;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@AllArgsConstructor
@Component
public class PaymentPointsRequest {

    CustomerRepository customerRepository;

    ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public void CustomerPointsListener(ObjectMapper objectMapper, CustomerRepository customerRepository) {
        this.objectMapper = objectMapper;
        this.customerRepository = customerRepository;
    }

    @RabbitListener(queues = {"customer-points-queue"})
    public void getPoints(@Payload String message) {
        try {
            CustomerMQ customerPoints = objectMapper.readValue(message, CustomerMQ.class);

            Long customerId = customerPoints.getCustomerId();
            Integer points = customerPoints.getPoints();
            Customer customer = customerRepository.findCustomerById(customerId);

            customer.setPoints(points + customer.getPoints());

            customerRepository.save(customer);
            System.out.println("Updated points: " + customer.getPoints());
        } catch (Exception e) {
            String message1 = e.getMessage();
            log.error(message1);
        }
    }
}
