package br.com.jardson.mscalculate.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RulesResponseDto {

    @NotNull
    @NotBlank
    public String category;
    @NotNull
    @NotBlank
    public Double parity;


}
