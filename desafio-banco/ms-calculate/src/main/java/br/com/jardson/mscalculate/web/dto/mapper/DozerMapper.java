package br.com.jardson.mscalculate.web.dto.mapper;

import br.com.jardson.mscalculate.entity.Rules;
import br.com.jardson.mscalculate.web.dto.RulesResponseDto;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class DozerMapper {

    private static ModelMapper mapper = new ModelMapper();

    public static Rules toCostomer(Rules createDTO) {
        return new ModelMapper().map(createDTO, Rules.class);
    }

    public static RulesResponseDto toDto(Rules customer) {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(customer, RulesResponseDto.class);
    }

    public static <O, D> D parseObject(O origin, Class<D> destination)
    {
        return mapper.map(origin, destination);
    }
    public static <O, D> List<D> parseListObjects(List<O> origin, Class<D> destination) {
        List<D> destinationObjects = new ArrayList<D>();
        for (Object o : origin) {
            destinationObjects.add(mapper.map(o, destination));
        }
        return destinationObjects;
    }
}
