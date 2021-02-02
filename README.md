# Coding Assignment (Payconiq)

## Introduction

Java BE REST Application (Stocks API).

## Assignment Requirements

- GET /api/stocks (get a list of stocks)
- GET /api/stocks/1 (get one stock from the list)
- PUT /api/stocks/1 (update the price of a single stock)
- POST /api/stocks (create a stock)

***

## How to run the application

The application has been developed in **Java 11**. In order to run it, simply go to the folder with your terminal and
type `mvn spring-boot:run` (you need to have Maven installed: http://maven.apache.org/install.html)
or `./mvnw spring-boot:run` (this uses Maven Wrapper).

The application will start on port 8080.

Then open your favorite browser and navigate to http://localhost:8080/swagger-ui.html.

To view the list of the Stocks, just navigate to http://localhost:8080/

***
## H2 admin panel

To access H2 admin panel, navigate to http://localhost:8080/h2-console and type `jdbc:h2:mem:stockdb` as JDBC URL and `sa` as both username and password.

***

## Libraries used

* **Spring Boot Starter Web**
  [_Starter for building web, including RESTful, applications using Spring MVC. Uses Tomcat as the default embedded
  container_]

* **Project Lombok**
  [_Project Lombok is a java library that automatically plugs into your editor and build tools, spicing up your java._]

* **Springfox Boot Starter & UI**
  [_JSON API documentation for spring based applications._]

* **Spring Boot Starter Data JPA**
  [_Starter for using Spring Data JPA with Hibernate._]

* **Flyway Core**
  [_DB Migration tool._]

* **Bean Validation API**
  [_JSR 380 is a specification of the Java API for bean validation, part of Jakarta EE and JavaSE._]

* **ModelMapper**
  [_ModelMapper is an intelligent, refactoring safe object mapping library that automatically maps objects to each
  other._]

* **Vaadin Spring Boot Starter with BoM**
  [_Used to build a simple UI._]

Used for testing:

* **Spring Boot Starter Test**
  [_Starter for testing Spring Boot applications with libraries including JUnit Jupiter, Hamcrest and Mockito._]

### Author

**Savino Bizzoca**
