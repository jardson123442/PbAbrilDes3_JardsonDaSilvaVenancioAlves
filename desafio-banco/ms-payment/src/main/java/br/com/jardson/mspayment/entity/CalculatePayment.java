package br.com.jardson.mspayment.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class CalculatePayment {

    @Id
    public Long id;
    public String category;
    public Double parity;
}
