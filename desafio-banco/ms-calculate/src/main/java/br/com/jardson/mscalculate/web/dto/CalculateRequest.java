package br.com.jardson.mscalculate.web.dto;

import lombok.Data;

@Data
public class CalculateRequest {

    private Long categoryId;
    private Double value;
}
