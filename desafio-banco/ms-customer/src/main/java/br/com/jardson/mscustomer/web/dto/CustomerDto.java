package br.com.jardson.mscustomer.web.dto;

import br.com.jardson.mscustomer.entity.Customer;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {

    private Long id;

    @Size(min = 11, max = 15, message = "{Size.clienteCreateDto.cpf}")
    private String cpf;

    @NotBlank(message = "{NotBlank.costomerCreateDto.nome}")
    @Size(min = 3, message = "{Size.customerCreateDto.name}")
    private String name;

    @Size(min = 4, max = 17, message = "{Pattern.customerCreateDto.gender}")
    private String gender;

    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDate birthDate;

    @NotBlank(message = "{NotBlank.costomerCreateDto.email}")
    @Email(message = "{Email.costomerCreateDto.email}",  regexp = "^[a-z0-9.+-]+@gmail\\.com$")
    private String email;

    private String photo;
}
