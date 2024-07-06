package br.com.jardson.mscustomer.config;

import br.com.jardson.mscustomer.entity.Customer;
import br.com.jardson.mscustomer.repository.CustomerRepository;
import br.com.jardson.mscustomer.web.dto.CustomerMQ;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;


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

    @RabbitListener(queues = { "customer-points-queue"})
    public void getPoints (@Payload Message message) throws JsonProcessingException {
        System.out.println(message);
        String payload = new String(message.getBody());
        System.out.println(payload);
        CustomerMQ customerPoints = objectMapper.readValue(payload, CustomerMQ.class);

        System.out.println("CustomerId: " + customerPoints.getCustomerId());
        System.out.println("Points: " + customerPoints.getPoints());
        Customer customer = customerRepository.getReferenceById(customerPoints.getCustomerId());
        Integer points = customerPoints.getPoints() + customer.getPoints();
        customer.setPoints(points);

        customerRepository.save(customer);
    }
}
