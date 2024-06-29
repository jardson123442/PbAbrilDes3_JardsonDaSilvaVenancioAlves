package br.com.jardson.mscustomer.common;

import br.com.jardson.mscustomer.entity.Customer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static br.com.jardson.mscustomer.entity.Customer.Gender.*;


public class CustomerConstants {
    static SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    static Date johnBirth;
    static Date tatooineBirth;
    static Date alderaanBirth;
    static Date yavinBirth;

    static {
        try {
            johnBirth = dateFormat.parse("03-04-2001");
            tatooineBirth = dateFormat.parse("01-01-2001");
            alderaanBirth = dateFormat.parse("01-01-2002");
            yavinBirth = dateFormat.parse("01-01-2003");

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static final Customer CUSTOMER = new Customer(1L, "12345678900", "John Doe", MALE, johnBirth, "john.doe@example.com", null, "null");
    public static final Customer INVALID_CUSTOMER = new Customer(null, "", "", null, null, "", null, "");

    public static final Customer TATOOINE = new Customer(1L, "12345678901", "Tatooine", MALE, tatooineBirth, "tatooine@example.com", null, "null");
    public static final Customer ALDERAAN = new Customer(2L, "12345678902", "Alderaan", FEMALE, alderaanBirth, "alderaan@example.com", null, "null");
    public static final Customer YAVINIV = new Customer(3L, "12345678903", "Yavin IV", MALE, yavinBirth, "yaviniv@example.com", null, "null");

}