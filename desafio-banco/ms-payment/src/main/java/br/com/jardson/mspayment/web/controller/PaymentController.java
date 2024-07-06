package br.com.jardson.mspayment.web.controller;

import br.com.jardson.mspayment.entity.Calculate;
import br.com.jardson.mspayment.entity.Customer;
import br.com.jardson.mspayment.entity.Payment;
import br.com.jardson.mspayment.entity.Rules;
import br.com.jardson.mspayment.exceptions.ResourceNotFoundException;
import br.com.jardson.mspayment.service.infra.CalculatePaymentService;
import br.com.jardson.mspayment.service.infra.CustomerPaymentService;
import br.com.jardson.mspayment.service.PaymentService;
import br.com.jardson.mspayment.web.dto.PaymentDto;
import br.com.jardson.mspayment.web.response.PaymentResponseDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/payments")
@Tag(name = "Payments", description = "Endpoints for Managing Payments")
public class PaymentController {

    public final CustomerPaymentService customerPaymentService;

    public final CalculatePaymentService calculatePaymentService;

    public final PaymentService paymentService;

    @Operation(summary = "New payment", description = "Resource to pay",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Resource created successfully",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = PaymentResponseDto.class))),
                    @ApiResponse(responseCode = "422", description = "Payment not processed due to invalid input data",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
            })
    @PostMapping
    public ResponseEntity<PaymentResponseDto> create(@RequestBody PaymentDto paymentDto) throws JsonProcessingException , ResourceNotFoundException {
        Customer customer = customerPaymentService.getCustomerPayment(paymentDto.getCustomerId());
        customer.setPoints(0);
        Rules rules = calculatePaymentService.getRulesById(paymentDto.getCategoryId());
        Calculate calculate = new Calculate(paymentDto.getCustomerId(), paymentDto.getTotal());
        calculatePaymentService.calculatePoints(calculate);

        Payment payment = new Payment();
        payment.setId(UUID.randomUUID().toString());
        payment.setCategoryId(paymentDto.getCategoryId());
        payment.setCustomerId(paymentDto.getCustomerId());
        payment.setTotal(customer.getPoints() + (rules.getParity() * paymentDto.getTotal()));
        payment.setCreatedDate(LocalDateTime.now());
        paymentService.save(payment);

        PaymentResponseDto paymentResponseDto = new PaymentResponseDto();
        paymentResponseDto.setCustomerId(paymentDto.getCustomerId());
        paymentResponseDto.setPoints(payment.getTotal());

        return ResponseEntity.ok(paymentResponseDto);
    }

    @GetMapping(value = "/user/{id}")
    public List<Payment> getAllPaymentsByCustomerId(@PathVariable Long id) {
        return paymentService.findAllPaymentsByCustomerId(id);
    }
}
