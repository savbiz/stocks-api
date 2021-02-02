# Coding Assignment (Payconiq)
##Introduction

Java BE REST Application (Stocks API).

##Assignment Requirements
- GET /api/stocks (get a list of stocks)
- GET /api/stocks/1 (get one stock from the list)
- PUT /api/stocks/1 (update the price of a single stock) 
- POST /api/stocks (create a stock)


***
## How to run the application

The application has been developed in **Java 11**.
In order to run it, simply go to the folder with your terminal and type `mvn spring-boot:run` (you need to have Maven installed: http://maven.apache.org/install.html) or `./mvnw spring-boot:run` (this uses Maven Wrapper).

The project also contains a **Dockerfile** that creates a docker image of the project:
- `mvn clean package`
- `docker build -t savbiz/stocks-api .`
- `docker images` (to fetch the image ID)
- `docker run -it {IMAGE_ID}`
***

##Libraries used TO FIX!!!!!!!
* **Spring Boot Starter**
[_Core starter, including auto-configuration support, logging and YAML_]

* **Project Lombok**
[_Project Lombok is a java library that automatically plugs into your editor and build tools, spicing up your java._]

Used for testing:
* **Spring Boot Starter Test**
[_Starter for testing Spring Boot applications with libraries including JUnit Jupiter, Hamcrest and Mockito._]

### Author
**Savino Bizzoca**
