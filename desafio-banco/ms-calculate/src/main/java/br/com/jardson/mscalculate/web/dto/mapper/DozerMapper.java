package br.com.jardson.mscalculate.web.dto.mapper;

import br.com.jardson.mscalculate.entity.Rules;
import br.com.jardson.mscalculate.web.response.RulesResponseDto;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class DozerMapper {

    private static ModelMapper mapper = new ModelMapper();

    public static RulesResponseDto toDto(Rules customer) {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(customer, RulesResponseDto.class);
    }

    public static <O, D> List<D> parseListObjects(List<O> origin, Class<D> destination) {
        List<D> destinationObjects = new ArrayList<D>();
        for (Object o : origin) {
            destinationObjects.add(mapper.map(o, destination));
        }
        return destinationObjects;
    }
}
