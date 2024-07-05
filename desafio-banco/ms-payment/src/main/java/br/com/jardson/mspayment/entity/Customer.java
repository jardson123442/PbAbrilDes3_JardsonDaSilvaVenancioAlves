package br.com.jardson.mspayment.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Customer {


    private Long id;
    private String cpf;
    private String name;
    private String gender;
    private Date birthDate;
    private String email;
    private Integer points;
    private String url_photo;
}
