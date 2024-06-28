package br.com.jardson.mscustomer.web.dto.mapper;

import br.com.jardson.mscustomer.entity.Customer;
import br.com.jardson.mscustomer.web.dto.CustomerDto;
import br.com.jardson.mscustomer.web.dto.CustomerResponseDto;
import org.modelmapper.ModelMapper;

public class DozerMapper {

    private static ModelMapper mapper = new ModelMapper();

    public static Customer toCostomer(CustomerDto createDTO) {
        return new ModelMapper().map(createDTO, Customer.class);
    }

    public static CustomerResponseDto toDto(Customer customer) {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(customer, CustomerResponseDto.class);
    }

    public static <O, D> D parseObject(O origin, Class<D> destination)
    {
        return mapper.map(origin, destination);
    }
}
