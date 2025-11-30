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
4. Maven

### Development install

1. Set up PostgreSQL database
2. Apply Liquibase scripts to patch the DB
      - Run `mvn liquibase:update`
        - Provide DB connection details with following environment variables:
          - DB_HOST
          - DB_PORT
          - DB_NAME
          - DB_PASSWORD
          - DB_USERNAME
3. Run the app with development profile
   - Add SPRING_PROFILES_ACTIVE env var with value 'dev' to the privously used env vars
   - Run `mvn spring-boot:run`

### Containerized install

1. Set up PostgreSQL database.
2. Apply Liquibase scripts to patch the DB
      - Run `mvn liquibase:update`
        - Provide DB connection details with following environment variables:
          - DB_HOST
          - DB_PORT
          - DB_NAME
          - DB_PASSWORD
          - DB_USERNAME
3. Build the app
   - Run `mvn clean package`
     - Provide the same env vars as for the liquibase update
4. (Optional) Modify the docker-compose.yaml file to better suite your needs
   - specifying the DB volume path etc.
5. Run the app and its DB
   - Run `docker compose up -d`

## TODOs

This project has some areas where it could (and should) be improved, but those were not covered by the assignment. One of them is Liquibase for example, which was already introduced for the sake of simplifying development.

### Testing

There are no tests implemented currently. Using some framework to add unit and/or integration tests like JUnit or TestNG in combination with Testcontainers is strongly advised.

### Deployment

CI/CD pipelines should be implemented in order to speed up the deployment process.

### Authentication

As required in the assignment, this application uses Basic Auth, which is unsafe. We should rather use a more robust authentication method like OAuth 2.0 or some equivalent.
`After implementing this, be sure to turn CSRF protection back on.`

### CI/CD

Pipelines should be created to automate the build and deployment process as well as automated testing before merges or after push to designated branches.

### Encryption

Right now the app uses unencrypted HTTP communication. In the future, HTTPS should be configured.
