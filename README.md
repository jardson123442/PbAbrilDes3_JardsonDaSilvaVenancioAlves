<h1 align="center">American Bank API</h1><br>

## `Index`

* [Project Description](#project-description)
* [Application Features and Demonstration](#application-features-and-demonstration)
* [Jacoco Tests](#application-features-and-demonstration)
* [Docker Compose](#application-features-and-demonstration)

## `Project Description`
<p>This project was created to simulate a bank with some basic functionalities.<br> 
RabbitMQ was used to send the points that the customer accumulated after purchases.</p><br>

## `Application Features and Demonstration`

### `Registrations:`<br>

This is where customer registration is done with the following fields.

- `POST -` http://localhost:8081/v1/customers <br>

```
{

  "cpf": "xxx.xxx.xxx-xx",
  "name": "String",
  "gender": "Male or Famale",
  "birthDate": "00-00-0000",
  "email": "string@gmail.com"
}
```


### `Search:`<br>

Here we search for registered customers.

- `GET -` http://localhost:8081/v1/customers/{id}<br>

### `Update:`<br>

Update customer information.

- `PUT -` http://localhost:8082/v1/customers/{id}

```
{
    "name": "String",
    "gender": "Male or Female",
    "birthDate": "00-00-0000",
    "email": "string@gmail.com",
    "photo": "null"
}
```

### `Delete:`<br>

Delete a customer.

- `DELETE -` http://localhost:8082/v1/customers/{id}

### `Register New Rule:`<br>


- `POST -` http://localhost:8080/v1/rules <br>

This is where the category rule is created.

```
{
  "category": "eletronics",
  "parity": 00
}
```
### `Calculate Points:`<br>

- `POST -` http://localhost:8080/v1/calculate <br>

This simulates the value of the points earned.

```
{
  "categoryId": 0,
  "value": 000
}
```


### `Search Rules by Id:`<br>

Here we search for rules by id.

- `GET -` http://localhost:8080/v1/rules/{id}<br>

### `Update Rules:`<br>

Here we update the rule.

- `PUT -` http://localhost:8080/v1/rules/{id} <br>

```
{
"category": "eletronics",
"parity": 00
}
```
### `Delete Rule:`

Here we delete the rule.

- `DELETE -` http://localhost:8080/v1/rules/{id} <br>


### `Make a Pay:`<br>

- `POST -` http://localhost:8082/v1/payments <br>

This is where a purchase is made, and the customer's points are added to their database.

```
{
  "customerId": 0,
  "categoryId": 0,
  "total": 0000
}
```
### `Search Payments by Id:`<br>

Here we search for payments by id.

- `GET -` http://localhost:8082/v1/payments/{id}<br>

### `Search Customer Payments by Id:`<br>

Here we search for customer payments by id.

- `GET -` http://localhost:8082/v1/payments/user/1 <br>


## `Jacoco Tests`
### `Customers:`<br>
![Customer Jacoco Tests](https://i.imgur.com/8PM071F.png)
### `Calculate:`<br>
![Customer Jacoco Tests](https://i.imgur.com/lIEsAd7.png)
### `Payments:`<br>
![Customer Jacoco Tests](https://i.imgur.com/tI0gs50.png)

## `Docker compose`

### `First Step:`<br>
Navigate to the root directory of all microservices and run the following command: `mvn clean package`

### `Second Step:`<br>
Go to the directory containing the `docker-compose.yml` file and run: `docker-compose up -d --build`

