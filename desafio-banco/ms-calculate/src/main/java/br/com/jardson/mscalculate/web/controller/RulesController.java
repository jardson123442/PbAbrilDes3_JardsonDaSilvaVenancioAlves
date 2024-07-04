package br.com.jardson.mscalculate.web.controller;

import br.com.jardson.mscalculate.entity.Rules;
import br.com.jardson.mscalculate.service.RulesService;
import br.com.jardson.mscalculate.web.dto.RulesResponseDto;
import br.com.jardson.mscalculate.web.dto.mapper.DozerMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.api.ErrorMessage;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/rules")
@Tag(name = "Rules", description = "Endpoints for create rules")
public class RulesController {

    @Autowired
    private RulesService service;

    @Operation(summary = "Create a new rule", description = "Resource to create a new rule",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Rule created successfully",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Rules.class))),
                    @ApiResponse(responseCode = "409", description = "Category data already registered in the system",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
                    @ApiResponse(responseCode = "422", description = "Resource not processed due to invalid input data",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
            })
    @PostMapping
    public ResponseEntity<RulesResponseDto> createRule(@RequestBody Rules rules) {
        RulesResponseDto response = new RulesResponseDto();
        BeanUtils.copyProperties(rules, response);
        service.create(rules);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Retrieve a rule by id", description = "Resource to find a rule by id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Rule retrieved successfully",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Rules.class))),
                    @ApiResponse(responseCode = "404", description = "Rule not found",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
            })
    @GetMapping("/{id}")
    public ResponseEntity<RulesResponseDto> getById(@PathVariable Long id) {
        Rules rules = service.getRulesById(id);
        RulesResponseDto customerDto = DozerMapper.toDto(rules);
        return ResponseEntity.ok(customerDto);
    }

    @Operation(summary = "Retrieve a rule by id", description = "Resource to find a rule by id",
            responses = {
                    @ApiResponse(responseCode = "400", description = "Bad Request",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Rules.class))),
                    @ApiResponse(responseCode = "404", description = "Resource not found",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
            })
    @GetMapping
    public List<Rules> findAll() {
        return service.findAll();
    }

    @Operation(summary = "Rules edit", description = "Change rule",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Rules successfully changed",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Rules.class))),
                    @ApiResponse(responseCode = "400", description = "Empty profile",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))),
                    @ApiResponse(responseCode = "422", description = "Request not processed by invalid customer",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
            })
    @PutMapping("/{id}")
    public ResponseEntity<RulesResponseDto> update(@PathVariable Long id, @RequestBody Rules rules) {
        rules = service.update(id, rules);
        RulesResponseDto rulesResponseDto = DozerMapper.toDto(rules);
        return ResponseEntity.ok().body(rulesResponseDto);
    }

    @Operation(summary = "Rule delete", description = "Delete rule",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Rule deleted",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Rules.class))),
                    @ApiResponse(responseCode = "400", description = "Empty rule",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))),
                    @ApiResponse(responseCode = "422", description = "Request not processed by invalid rule",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
            })
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

