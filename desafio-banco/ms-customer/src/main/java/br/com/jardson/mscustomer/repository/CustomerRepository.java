package br.com.jardson.mscustomer.repository;

import br.com.jardson.mscustomer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    boolean existsCustomerByCpf(String cpf);
}
