package br.com.jardson.mscustomer.web.dto.mapper;

import br.com.jardson.mscustomer.entity.Customer;
import br.com.jardson.mscustomer.web.dto.CustomerDto;
import br.com.jardson.mscustomer.web.response.CustomerResponseDto;
import org.modelmapper.ModelMapper;

public class DozerMapper {

    public static Customer toCustomer(CustomerDto createDTO) {
        return new ModelMapper().map(createDTO, Customer.class);
    }

    public static CustomerResponseDto toDto(Customer customer) {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(customer, CustomerResponseDto.class);
    }
}
