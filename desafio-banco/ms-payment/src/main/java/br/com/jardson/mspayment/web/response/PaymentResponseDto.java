package br.com.jardson.mspayment.web.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PaymentResponseDto {

    public Long customerId;
    public Integer points;
}
