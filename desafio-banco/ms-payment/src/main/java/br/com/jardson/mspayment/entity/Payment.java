package br.com.jardson.mspayment.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "payments")
public class Payment implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private UUID id;

    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "category_id")
    private Long categoryId;

    @Column(name = "total", nullable = false)
    private Integer total;  // Alterei para Double para corresponder ao cálculo de pontos

    @Column(name = "create_date", updatable = false)
    private Timestamp createdDate;

    @PrePersist
    protected void onCreate() {
        createdDate = new Timestamp(System.currentTimeMillis());
    }

}
