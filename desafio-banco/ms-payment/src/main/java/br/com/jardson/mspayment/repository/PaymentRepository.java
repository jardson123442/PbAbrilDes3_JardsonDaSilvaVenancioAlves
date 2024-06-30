package br.com.jardson.mspayment.repository;

import br.com.jardson.mspayment.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
