package br.com.jardson.mscustomer.web;

import br.com.jardson.mscustomer.entity.Customer;
import br.com.jardson.mscustomer.service.CustomerService;
import br.com.jardson.mscustomer.web.controller.CustomerController;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static br.com.jardson.mscustomer.common.CustomerConstants.CUSTOMER;
import static br.com.jardson.mscustomer.common.CustomerConstants.INVALID_CUSTOMER;
import static br.com.jardson.mscustomer.entity.Customer.Gender.MALE;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @MockBean
  private CustomerService customerService;

  @Test
  public void createCustomer_WithValidData_ReturnsCreated() throws Exception {
    when(customerService.save(CUSTOMER)).thenReturn(CUSTOMER);

    mockMvc
        .perform(
            post("/customers")
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$").value(CUSTOMER));

    assertNotNull(CUSTOMER);

    assertNotNull(CUSTOMER.getId());
    assertNotNull(CUSTOMER.getCpf());
    assertNotNull(CUSTOMER.getName());
    assertNotNull(CUSTOMER.getGender());
    assertNotNull(CUSTOMER.getBirthDate());

    assertTrue(CUSTOMER.getId() > 0);

    assertEquals("John Doe", CUSTOMER.getName());
    assertEquals("12345678900", CUSTOMER.getCpf());
    assertEquals(MALE, CUSTOMER.getGender());
    assertEquals("john.doe@example.com", CUSTOMER.getEmail());
  }

  @Test
  public void createCustomer_WithInvalidData_ReturnsBadRequest() throws Exception {
    Customer emptyCustomer = new Customer();

    mockMvc
        .perform(
            post("/customers").content(objectMapper.writeValueAsString(emptyCustomer))
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isUnprocessableEntity());
    mockMvc
        .perform(
            post("/customers").content(objectMapper.writeValueAsString(INVALID_CUSTOMER))
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isUnprocessableEntity());
  }

  @Test
  public void createCustomer_WithExistingName_ReturnsConflict() throws Exception {
    when(customerService.save(any())).thenThrow(DataIntegrityViolationException.class);

    mockMvc
        .perform(
            post("/customers").content(objectMapper.writeValueAsString(CUSTOMER))
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isConflict());
  }

  @Test
  public void getCustomer_ByExistingId_ReturnsCustomer() throws Exception {
    when(customerService.findById(1L)).thenReturn(CUSTOMER);

    mockMvc
        .perform(
            get("/customers/1"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").value(CUSTOMER));
  }

  @Test
  public void getCustomer_ByUnexistingId_ReturnsNotFound() throws Exception {
    mockMvc.perform(get("/customers/1"))
        .andExpect(status().isNotFound());
  }

  @Test
  public void getCustomer_ByUnexistingName_ReturnsNotFound() throws Exception {
    mockMvc.perform(get("/customers/name/1"))
        .andExpect(status().isNotFound());
  }


  @Test
  public void removeCustomer_WithExistingId_ReturnsNoContent() throws Exception {
    mockMvc.perform(delete("/customers/1"))
        .andExpect(status().isNoContent());
  }

  @Test
  public void removeCustomer_WithUnexistingId_ReturnsNotFound() throws Exception {
    final Long CustomerId = 1L;

    doThrow(new EmptyResultDataAccessException(1)).when(customerService).delete(CustomerId);

    mockMvc.perform(delete("/Customers/" + CustomerId))
        .andExpect(status().isNotFound());
  }
}
