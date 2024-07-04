package br.com.jardson.mspayment.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Calculate {

    public Long calculateId;
    public String category;
    public Double parity;
}
