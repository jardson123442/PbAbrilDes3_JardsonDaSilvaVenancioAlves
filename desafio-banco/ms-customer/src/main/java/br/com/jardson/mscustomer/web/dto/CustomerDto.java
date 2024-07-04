package br.com.jardson.mscustomer.web.dto;

import br.com.jardson.mscustomer.entity.Customer;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
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

    @NotNull(message = "CPF is required")
    @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}", message = "CPF must be in the format XXX.XXX.XXX-XX")
    private String cpf;

    @NotBlank(message = "{NotBlank.costomerCreateDto.nome}")
    @Size(min = 3, message = "{Size.customerCreateDto.name}")
    private String name;

    @NotNull(message = "Gender is required")
    //@Pattern(regexp = "Female|Male", message = "Gender must be either Female or Male")
    private String gender;

    @NotNull
    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date birthDate;

    @NotBlank(message = "{NotBlank.costomerCreateDto.email}")
    @Email(message = "{Email.costomerCreateDto.email}",  regexp = "^[a-z0-9.+-]+@gmail\\.com$")
    private String email;

    private String photo;
}
