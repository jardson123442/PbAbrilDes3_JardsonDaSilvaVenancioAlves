package br.com.jardson.mspayment.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private String message;

}
