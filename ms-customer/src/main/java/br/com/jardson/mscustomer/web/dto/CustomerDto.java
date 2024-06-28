package br.com.jardson.mscustomer.web.dto;

import br.com.jardson.mscustomer.entity.Customer;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

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

    @NotBlank(message = "{NotBlank.customerCreateDto.gender}")
    @Pattern(regexp = "^(FEMALE|MALE)$", message = "{Pattern.customerCreateDto.gender}")
    private Customer.Gender gender;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date birthDate;

    @NotBlank(message = "{NotBlank.costomerCreateDto.email}")
    @Email(message = "{Email.costomerCreateDto.email}",  regexp = "^[a-z0-9.+-]+@gmail\\.com$")
    private String email;

    private String photo;
}
