package br.com.jardson.mspayment.web.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CustomerResponseDto {

    public Long id;
    public List<PaymentResponseDto> payments;
}
