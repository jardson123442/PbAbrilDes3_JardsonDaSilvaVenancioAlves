package br.com.jardson.mscustomer.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "costomers")
public class Customer implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(name = "cpf", nullable = false, unique = true, length = 15)
    private String cpf;

    @Column(name = "name", nullable = false, length = 200)
    private String name;

    @Column(name = "gender", nullable = false, length = 6)
    private Gender gender;

    @Column(nullable = false)
    private Date birthDate;

    @Column(unique = true, nullable = false)
    private String email;
    private Integer points;
    private String url_photo;

    public enum Gender {
        FEMALE, MALE
    }

}
