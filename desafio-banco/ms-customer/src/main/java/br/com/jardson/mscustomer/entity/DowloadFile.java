package br.com.jardson.mscustomer.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DowloadFile implements Serializable {

    private String fileDowloadUrl;
}
