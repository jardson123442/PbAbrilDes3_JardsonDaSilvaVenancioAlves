package br.com.jardson.mscustomer.web.response;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CustomerResponseDto {

    private String cpf;
    private String name;
    private String gender;
    private Date birthDate;
    private String email;
    private Integer points;
    private String url_photo;
}
