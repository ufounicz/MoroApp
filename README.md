# MoroApp

MoroApp is a simple application based on Spring Boot providing REST API for basic CRUD handling of users secured by Basic Auth.

`MoroApp is an implementation of a coding assignment for a job interview. It is not a production ready application, but rather a result of step by step assignment with some additional tweaks.`

## Tech stack

- **Spring Boot** - Main Framework
- **Java 21**
- **jOOQ** - EO generation and DB connection
- **Liquibase** - Database change management
- **Spring Security** - Auth handling, using Basic Auth
- **PostgreSQL** - Database
- **Docker** - Containerization

## Dev tools used

- **IntelliJ IDEA** - IDE
- **Postman** - API testing
- **pgAdmin** - DB handling
- **ChatGPT** - Consultation, code generation

## Installation

Running this application is fairly easy. There are two ways we can run this app, either with Docker or without it.

### Prerequisites

1. Running PostgreSQL database.
2. Java 21
3. Docker (if we want the containerized version)

### Development install

1. Set up PostgreSQL database and provide the app with connection information in form of these environmant variables:
   1. DB_HOST
   2. DB_PORT
   3. DB_NAME
   4. DB_USERNAME
   5. DB_PASSWORD
2. Apply liquibase scripts to patch the DB
   - Run `mvn liquibase:update`
3. Run the app with development profile
   - Add SPRING_PROFILES_ACTIVE env var with value 'dev'
   - Run `mvn spring-boot:run`

### Containerized install

1. Set up PostgreSQL database and provide the app with connection information in form of these environmant variables:
   1. DB_HOST
   2. DB_PORT
   3. DB_NAME
   4. DB_USERNAME
   5. DB_PASSWORD
2. Apply liquibase scripts to patch the DB
   - Run `mvn liquibase:update`
3. Modify docker-compose.yml so that the env vars supplied match your setup.
4. Containerized version needs `prod` spring profile! It affects how liquibase works.
5. Run `docker compose up -d`

## TODOs

This project has some areas where it could (and should) be improved, but those were not covered by the assignment. One of them is Liquibase for example, which was already introduced for the sake of simplifying development.

### Testing

There are no tests implemented currently. Using some framework to add unit and/or integration tests like JUnit or TestNG in combination with Testcontainers is strongly advised.

### Sensitive data

Credentials and other sensitive data should be taken out of the property files and pom and rather passed as an environment variables, which then can be read by Spring. This will also be handy when deploying the app in different environments.

### Deployment

CI/CD pipelines should be implemented in order to speed up the deployment process.

### Authentication

As required in the assignment, this application uses Basic Auth, which is unsafe. We should rather use a more robust authentication method like OAuth 2.0 or some equivalent.
`After implementing this, be sure to turn CSRF protection back on.`

### CI/CD

CI/CD pipelines for testing/deployment should be created for faster and easier development and deployment.

### Encryption

Right now the app uses unencrypted HTTP communication. In the future, HTTPS should be configured.
