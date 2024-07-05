package br.com.jardson.mspayment.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerPayment {

    public Customer customer;
    public Rules category;
    public Integer points;
}
