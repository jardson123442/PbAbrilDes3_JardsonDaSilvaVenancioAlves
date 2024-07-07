package br.com.jardson.mscustomer.web;

import br.com.jardson.mscustomer.entity.Customer;
import br.com.jardson.mscustomer.exception.CpfAlreadyExistsException;
import br.com.jardson.mscustomer.exception.InvalidGenderException;
import br.com.jardson.mscustomer.exception.RequiredObjectIsNullException;
import br.com.jardson.mscustomer.exception.ResourceNotFoundException;
import br.com.jardson.mscustomer.repository.CustomerRepository;
import br.com.jardson.mscustomer.service.CustomerService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

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

        customer = new Customer(1L, "123.456.789-00", "John Doe", "MALE", new Date(10,10,2000) , "john.doe@gmail.com" ,0 ,null);
    }

    @Test
    void testFindById() {
        when(repository.findById(customer.getId())).thenReturn(Optional.of(customer));

        Customer customer1 = service.getById(customer.getId());

        assertEquals(customer, customer1);
        assertNotNull(customer1.getId());
        assertNotNull(customer1.getName());
        assertNotNull(customer1.getCpf());
        assertNotNull(customer1.getGender());
        assertNotNull(customer1.getBirthDate());
        assertNotNull(customer1.getEmail());
        verify(repository).findById(customer.getId());
        verifyNoMoreInteractions(repository);

        assertEquals(customer, customer1);
        verify(repository).findById(1L);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void testGetByIdThrowsResourceNotFoundException() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        ResourceNotFoundException thrown = assertThrows(
                ResourceNotFoundException.class,
                () -> service.getById(1L),
                "Expected getById() to throw, but it didn't"
        );

        assertEquals("Customer not found with id 1", thrown.getMessage());
        verify(repository).findById(1L);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void testSaveCustomer() {;

        when(repository.save(customer)).thenReturn(customer);

        Customer result = service.save(customer);

        assertNotNull(result);
        assertEquals("John Doe", result.getName());
        assertEquals("123.456.789-00", result.getCpf());
        assertEquals("MALE", result.getGender());
        assertEquals(new Date(10,10,2000), result.getBirthDate());
        assertEquals("john.doe@gmail.com", result.getEmail());

        verify(repository).existsCustomerByCpf(customer.getCpf());
        verify(repository).save(customer);
        verifyNoMoreInteractions(repository);
        verify(repository, times(1)).save(customer);
    }

    @Test
    void testSaveThrowsCpfAlreadyExistsException() {
        Customer customer = new Customer();
        customer.setCpf("12345678900");

        when(repository.existsCustomerByCpf(customer.getCpf())).thenReturn(true);

        CpfAlreadyExistsException thrown = assertThrows(
                CpfAlreadyExistsException.class,
                () -> service.save(customer),
                "Expected save() to throw, but it didn't"
        );

        assertEquals("CPF already exists.", thrown.getMessage());
        verify(repository).existsCustomerByCpf(customer.getCpf());
        verifyNoMoreInteractions(repository);
    }

    @Test
    void testSaveThrowsInvalidGenderException() {
        Customer customer = new Customer();
        customer.setGender("InvalidGender");

        when(repository.existsCustomerByCpf(customer.getCpf())).thenReturn(false);

        InvalidGenderException thrown = assertThrows(
                InvalidGenderException.class,
                () -> service.save(customer),
                "Expected save() to throw, but it didn't"
        );

        assertEquals("Gender must be either 'Female' or 'Male'.", thrown.getMessage());
        verify(repository).existsCustomerByCpf(customer.getCpf());
        verifyNoMoreInteractions(repository);
    }


    @Test
    void testUpdate() {
        Customer customer = new Customer();
        customer.setId(1L);

        Customer updatedCustomer = new Customer();
        updatedCustomer.setName("New Name");
        updatedCustomer.setBirthDate(new Date(10, 4, 1990)); // Correção da data (ano - 1900, mês - 1)
        updatedCustomer.setEmail("new@email.com");
        updatedCustomer.setGender("Male");
        updatedCustomer.setPoints(100);

        when(repository.getReferenceById(1L)).thenReturn(customer);
        when(repository.save(customer)).thenReturn(customer);

        Customer result = service.update(1L, updatedCustomer);

        assertNotNull(result);
        assertEquals("New Name", result.getName());
        assertEquals(100, result.getPoints());
        assertEquals("Male", result.getGender());
        assertEquals("new@email.com", result.getEmail());
        assertEquals(new Date(10, 4, 1990), result.getBirthDate());

        verify(repository).getReferenceById(1L);
        verify(repository).save(customer);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void testDeleteSuccess() {
        Customer customer = new Customer();
        customer.setId(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(customer));

        service.delete(1L);

        verify(repository).findById(1L);
        verify(repository).delete(customer);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void testDeleteThrowsResourceNotFoundException() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        ResourceNotFoundException thrown = assertThrows(
                ResourceNotFoundException.class,
                () -> service.delete(1L),
                "Expected delete() to throw, but it didn't"
        );

        assertEquals("No records found for this ID!", thrown.getMessage());
        verify(repository).findById(1L);
        verifyNoMoreInteractions(repository);
    }
}
