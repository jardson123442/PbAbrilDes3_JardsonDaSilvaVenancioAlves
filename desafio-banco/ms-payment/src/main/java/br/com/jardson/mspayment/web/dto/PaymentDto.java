package br.com.jardson.mspayment.web.dto;

import lombok.Data;

@Data
public class PaymentDto {

    private Long customerId;
    private Long categoryId;
    private Double total;
}
