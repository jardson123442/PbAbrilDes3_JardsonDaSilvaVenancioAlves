package br.com.jardson.mspayment.web.response;

import lombok.Data;

import java.util.UUID;

@Data
public class PaymentResponseDto {

    public Long customerId;
    public Double points;
}
