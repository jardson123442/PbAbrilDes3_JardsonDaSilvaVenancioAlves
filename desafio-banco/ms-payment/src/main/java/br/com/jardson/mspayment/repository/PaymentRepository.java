package br.com.jardson.mspayment.repository;

import br.com.jardson.mspayment.entity.Payment;
import br.com.jardson.mspayment.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, String> {

    List<Payment> findByCustomerId(Long customerId);
}
