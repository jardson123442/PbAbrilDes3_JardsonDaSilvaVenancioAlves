package br.com.jardson.mscustomer.common;

import br.com.jardson.mscustomer.entity.Customer;

import java.util.Date;

public class CustomerConstants {

    public static final Customer CUSTOMER = new Customer(1L, "12345678900", "John Doe", "Male", new Date(3,4,2001), "john.doe@example.com", null, "null");
    public static final Customer INVALID_CUSTOMER = new Customer(null, "", "", null, null, "", null, "");

    public static final Customer TATOOINE = new Customer(1L, "12345678901", "Tatooine", "Male", new Date(3,4,2002), "tatooine@example.com", null, "null");
    public static final Customer ALDERAAN = new Customer(2L, "12345678902", "Alderaan", "Female", new Date(3,4,2003), "alderaan@example.com", null, "null");
    public static final Customer YAVINIV = new Customer(3L, "12345678903", "Yavin IV", "Male", new Date(3,4,2004), "yaviniv@example.com", null, "null");

}
