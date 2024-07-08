package br.com.jardson.mspayment.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.io.Serializable;
import java.time.LocalDateTime;

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

    @CreatedDate
    @Column(name = "create_date")
    private LocalDateTime createdDate;
}
