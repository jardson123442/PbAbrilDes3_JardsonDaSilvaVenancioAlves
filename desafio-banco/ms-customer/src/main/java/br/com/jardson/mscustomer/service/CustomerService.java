package br.com.jardson.mscustomer.service;

import br.com.jardson.mscustomer.entity.Customer;
import br.com.jardson.mscustomer.exception.CpfAlreadyExistsException;
import br.com.jardson.mscustomer.exception.InvalidGenderException;
import br.com.jardson.mscustomer.exception.ResourceNotFoundException;
import br.com.jardson.mscustomer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    public CustomerRepository repository;

    public Customer findById(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Usuário não encontrado."));
    }

    public Customer save(Customer dto) {
        return repository.save(dto);
    }

    public Customer update(Long id, Customer customer) {
        Customer entity = repository.getReferenceById(id);
        updateData(entity, customer);
        return repository.save(entity);
    }

    public void delete(Long id) {

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        repository.delete(entity);
    }

    private void updateData(Customer entity, Customer customer) {
        entity.setName(customer.getName());
        entity.setEmail(customer.getEmail());
        entity.setBirthDate(customer.getBirthDate());
        entity.setGender(customer.getGender());
    }
}
