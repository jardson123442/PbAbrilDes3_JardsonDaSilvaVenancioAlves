package br.com.jardson.mspayment.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Payment implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private UUID id;
    @Column(name = "CUSTOMER_ID")
    private Long customerId;
    @Column(name = "CATEGORY")
    private Long categoryId;
    @Column(name = "TOTAL")
    private Double total;
    @Column(name = "CREATED_DATE")
    private LocalDateTime createdDate = LocalDateTime.now();

}
