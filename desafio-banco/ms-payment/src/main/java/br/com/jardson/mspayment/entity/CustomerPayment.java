package br.com.jardson.mspayment.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;


public class CustomerPayment {

    private Long id;
    private String name;
    private Date birthDate;
    private Payment payment;
    private BigDecimal points;
}
