package br.com.jardson.mspayment.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Payment implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;
    @Column(name = "CUSTOMER_ID")
    private Customer customer;
    @Column(name = "CATEGORY")
    private Category category;
    @Column(name = "TOTAL")
    private BigDecimal total;
    @Column(name = "CREATED_DATE")
    private LocalDateTime createdDate;

}
