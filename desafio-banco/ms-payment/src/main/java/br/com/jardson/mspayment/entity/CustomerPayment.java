package br.com.jardson.mspayment.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

public class CustomerPayment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    private String cpf;
    private String name;
    private Date birthDate;
    private String email;
    @ManyToOne
    @JoinColumn(name = "id_payment")
    private Payment payment;
    private BigDecimal points;
    private String url_photo;
}
