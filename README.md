# Stocks API

Java/Spring Boot REST API for managing stock prices, with JPA, an in-memory H2 database, Flyway migrations and a small Vaadin UI.

Originally developed as a backend coding exercise in 2021. The original Java 11 and Spring Boot 2.4.2 implementation is retained; this is a historical example, not a maintained production service.

## Run locally

With Java 11 installed, run from the repository root:

```sh
./mvnw spring-boot:run
```

The application listens on port 8080:

- [Stock list](http://localhost:8080/) — Vaadin UI.
- [API documentation](http://localhost:8080/swagger-ui.html) — Springfox Swagger UI.
- [H2 console](http://localhost:8080/h2-console) — use the database URL and local development settings in [application.properties](src/main/resources/application.properties).

The database is in memory. The checked-in database credentials are local demonstration defaults; do not use this configuration for a public deployment. The legacy build has not been revalidated as part of this documentation cleanup.

## Endpoints

| Method | Path | Operation |
| --- | --- | --- |
| GET | `/api/stocks` | List stocks |
| GET | `/api/stocks/{id}` | Retrieve a stock |
| PUT | `/api/stocks/{id}` | Update a stock price |
| POST | `/api/stocks` | Create a stock |

Run the existing tests with `./mvnw test`. A [Postman collection](support/testdata/postman/stocks-api.postman_collection.json) is also included.
