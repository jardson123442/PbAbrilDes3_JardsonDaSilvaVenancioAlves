package br.com.jardson.mspayment.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.time.Instant;
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
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column
    private String id;

    @Column(name = "customer_id", nullable = false)
    private Long customerId;

    @Column(name = "category_id", nullable = false)
    private Long categoryId;

    @Column(name = "total", nullable = false)
    private Integer total;

    @Column(name = "create_date", nullable = false, updatable = false)
    private Instant createdDate;
}
