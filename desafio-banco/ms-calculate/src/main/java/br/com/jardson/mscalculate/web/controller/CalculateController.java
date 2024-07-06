package br.com.jardson.mscalculate.web.controller;

import br.com.jardson.mscalculate.entity.Rules;
import br.com.jardson.mscalculate.service.RulesService;
import br.com.jardson.mscalculate.web.dto.CalculateRequest;
import br.com.jardson.mscalculate.web.dto.TotalResponseDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/calculate")
@Tag(name = "Calculate", description = "Endpoints for calculate total")
public class CalculateController {

    @Autowired
    RulesService service;

    @PostMapping
    public ResponseEntity<TotalResponseDto> calculatePoints(@RequestBody CalculateRequest request) {
     
        if (request.getCategoryId() == null || request.getValue() == null) {
            throw new IllegalArgumentException("Todos os campos são obrigatórios");
        }
        Rules rule = service.getRulesById(request.getCategoryId());
        Double parity = rule.getParity() * request.getValue();
        TotalResponseDto response = new TotalResponseDto();
        response.setTotal(parity);
        return ResponseEntity.ok(response);
    }
}
