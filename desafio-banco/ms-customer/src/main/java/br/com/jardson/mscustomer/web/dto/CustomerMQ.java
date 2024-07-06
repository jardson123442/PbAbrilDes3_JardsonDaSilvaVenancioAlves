package br.com.jardson.mscustomer.web.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CustomerMQ {

    private Long customerId;
    private Integer points;
}
