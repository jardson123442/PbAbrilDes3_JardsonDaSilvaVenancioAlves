package br.com.jardson.mscustomer.web;

import br.com.jardson.mscustomer.entity.Customer;
import br.com.jardson.mscustomer.repository.CustomerRepository;
import br.com.jardson.mscustomer.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    @InjectMocks
    CustomerService service;

    @Mock
    CustomerRepository repository;

    Customer customer;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        customer = new Customer(1L, "12345678900", "John Doe", "MALE", LocalDate.parse("10/08/1998", formatter) , "john.doe@gmail.com" ,0 ,"null");
    }

    @Test
    void testFindById() {
        when(repository.findById(customer.getId())).thenReturn(Optional.of(customer));

        Customer customer1 = service.getById(customer.getId());

        assertEquals(customer, customer1);
        verify(repository).findById(customer.getId());
        verifyNoMoreInteractions(repository);
    }

    @Test
    void testSaveCustomer() {;

        when(repository.save(customer)).thenReturn(customer);

        Customer result = service.save(customer);

        assertNotNull(result);
        assertEquals("John Doe", result.getName());
        assertEquals("12345678900", result.getCpf());
        assertEquals("MALE", result.getGender());
        assertEquals(LocalDate.of(1998, 8, 10), result.getBirthDate());
        assertEquals("john.doe@gmail.com", result.getEmail());

        verify(repository, times(1)).save(customer);
    }


    @Test
    void testUpdate() {
        Customer updatedCustomer = new Customer();
        updatedCustomer.setName("New Name");

        when(repository.getReferenceById(1L)).thenReturn(customer);
        when(repository.save(customer)).thenReturn(customer);

        Customer result = service.update(1L, updatedCustomer);

        assertNotNull(result);
        assertEquals("New Name", result.getName());
        verify(repository, times(1)).getReferenceById(1L);
        verify(repository, times(1)).save(customer);
    }
}
