package br.com.jardson.mscustomer.service;

import br.com.jardson.mscustomer.entity.Customer;
import br.com.jardson.mscustomer.exception.CpfAlreadyExistsException;
import br.com.jardson.mscustomer.exception.InvalidGenderException;
import br.com.jardson.mscustomer.exception.ResourceNotFoundException;
import br.com.jardson.mscustomer.repository.CustomerRepository;
import com.amazonaws.services.s3.AmazonS3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Value("${aws.bucket.name}")
    private String bucketName;

    @Autowired
    private AmazonS3 s3Client;

    @Autowired
    public CustomerRepository repository;

    public Customer getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer not found with id " + id));
    }

    public Customer save(Customer dto) {
        if (repository.existsCustomerByCpf(dto.getCpf())) {
            throw new CpfAlreadyExistsException("CPF already exists.");
        }
        if (!dto.getGender().equalsIgnoreCase("Female") && !dto.getGender().equalsIgnoreCase("Male")) {
            throw new InvalidGenderException("Gender must be either 'Female' or 'Male'.");
        }
        String imgUrl = null;
        dto.setUrl_photo(imgUrl);

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
        //entity.setBirthDate(customer.getBirthDate());
        entity.setGender(customer.getGender());
    }
}
