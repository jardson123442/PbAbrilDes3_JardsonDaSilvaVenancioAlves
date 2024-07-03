package br.com.jardson.mscustomer.common;

import br.com.jardson.mscustomer.entity.Customer;
import org.joda.time.DateTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;



public class CustomerConstants {



    public static final Customer CUSTOMER = new Customer(1L, "12345678900", "John Doe", "MALE", new Date("20,8,2000"), "john.doe@example.com", 0, "null");
    public static final Customer INVALID_CUSTOMER = new Customer(null, "", "", null, null ,null,  0, "");

    public static final Customer TATOOINE = new Customer(1L, "12345678901", "Tatooine", "MALE", new Date("20,8,2001"), "tatooine@example.com", 0, "null");
    public static final Customer ALDERAAN = new Customer(2L, "12345678902", "Alderaan", "FEMALE", new Date("20,8,2002"), "alderaan@example.com", 0, "null");
    public static final Customer YAVINIV = new Customer(3L, "12345678903", "Yavin IV", "MALE", new Date("20,8,2003"), "yaviniv@example.com", 0, "null");

}