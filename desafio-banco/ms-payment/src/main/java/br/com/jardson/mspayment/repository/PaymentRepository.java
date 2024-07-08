package br.com.jardson.mspayment.repository;

import br.com.jardson.mspayment.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, String> {

    List<Payment> findByCustomerId(Long customerId);
}
