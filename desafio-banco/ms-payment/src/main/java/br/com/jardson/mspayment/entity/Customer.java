package br.com.jardson.mspayment.entity;

import lombok.*;

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
