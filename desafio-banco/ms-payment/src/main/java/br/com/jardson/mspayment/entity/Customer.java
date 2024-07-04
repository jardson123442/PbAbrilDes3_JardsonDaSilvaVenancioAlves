package br.com.jardson.mspayment.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    private Long customerId;
    private String name;
    private Date birthDate;
    private Payment payment;
    private BigDecimal points;
}
