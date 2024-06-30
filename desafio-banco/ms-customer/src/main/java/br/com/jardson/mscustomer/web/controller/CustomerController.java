package br.com.jardson.mscustomer.web.controller;

import br.com.jardson.mscustomer.entity.Customer;
import br.com.jardson.mscustomer.exception.CpfAlreadyExistsException;
import br.com.jardson.mscustomer.exception.InvalidGenderException;
import br.com.jardson.mscustomer.service.CustomerService;
import br.com.jardson.mscustomer.web.dto.CustomerDto;
import br.com.jardson.mscustomer.web.dto.CustomerResponseDto;
import br.com.jardson.mscustomer.web.dto.mapper.DozerMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.api.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/v1/customers")
@Tag(name = "People", description = "Endpoints for Managing People")
public class CustomerController {

    @Autowired
    public CustomerService service;

    @Operation(summary = "Retrieve a customer by id", description = "Resource to find a new customer by id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Resource retrieved successfully",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CustomerDto.class))),
                    @ApiResponse(responseCode = "403", description = "User without permission to access this resource",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
                    @ApiResponse(responseCode = "404", description = "Resource not found",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
            })
    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponseDto> getById(@PathVariable Long id) {
        Customer customer = service.findById(id);
        CustomerResponseDto customerDto = DozerMapper.toDto(customer);
        return ResponseEntity.ok(customerDto);
    }

    @Operation(summary = "Create a new customer", description = "Resource to create a new customer",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Resource created successfully",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CustomerResponseDto.class))),
                    @ApiResponse(responseCode = "409", description = "Customer data already registered in the system",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
                    @ApiResponse(responseCode = "422", description = "Resource not processed due to invalid input data",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
            })
    @PostMapping
    public ResponseEntity<CustomerResponseDto> create(@RequestBody CustomerDto customerDto) {
        Customer customer = service.save(DozerMapper.toCustomer(customerDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(DozerMapper.toDto(customer));
    }

    @Operation(summary = "Customer edit", description = "Change customer profile",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Profile successfully changed",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CustomerDto.class))),
                    @ApiResponse(responseCode = "400", description = "Empty profile",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))),
                    @ApiResponse(responseCode = "422", description = "Request not processed by invalid customer",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
            })
    @PutMapping("/{id}")
    public ResponseEntity<CustomerResponseDto> update(@PathVariable Long id, @RequestBody Customer customer) {
        customer = service.update(id, customer);
        CustomerResponseDto responseDto = DozerMapper.toDto(customer);
        return ResponseEntity.ok().body(responseDto);
    }

    @Operation(summary = "Customer delete", description = "Delete customer profile",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Profile deleted",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CustomerDto.class))),
                    @ApiResponse(responseCode = "400", description = "Empty profile",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))),
                    @ApiResponse(responseCode = "422", description = "Request not processed by invalid customer",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
            })
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
