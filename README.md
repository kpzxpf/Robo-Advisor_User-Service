# User Service

## Overview

User Service is a Spring Boot application that provides REST API endpoints for managing user information. It allows
retrieving and updating user data with proper validation and error handling.

## Features

- User data management (retrieve and update)
- Role-based user structure
- Data validation
- Error handling
- Database persistence with PostgreSQL
- Containerization with Docker

## Technology Stack

- Java 17+
- Spring Boot 3.4.5
- Spring Data JPA
- PostgreSQL
- Flyway for database migrations
- MapStruct for object mapping
- Lombok for reducing boilerplate code
- Docker for containerization

## API Documentation

### Endpoints

#### Get User by ID

- **URL**: `/api/users/{id}`
- **Method**: GET
- **Path Parameters**:
    - `id` - User ID (long)
- **Response**: UserDto object with user information
- **Error Responses**:
    - 404 Not Found - If user with specified ID doesn't exist
    - 500 Internal Server Error - If server encounters an error

#### Update User

- **URL**: `/api/users/`
- **Method**: PUT
- **Request Body**: UserDto object with updated user information
- **Response**: Updated UserDto object
- **Error Responses**:
    - 400 Bad Request - If validation fails
    - 404 Not Found - If user with specified ID doesn't exist
    - 500 Internal Server Error - If server encounters an error

### Data Models

#### UserDto

```json
{
  "id": 1,
  "username": "johndoe",
  "email": "john.doe@example.com",
  "firstName": "John",
  "lastName": "Doe"
}
```

## Setup Instructions

### Prerequisites

- Java 17 or higher
- Docker and Docker Compose (for containerized deployment)
- PostgreSQL (if running without Docker)

### Configuration

The application is configured via `application.yaml` file:

```yaml
spring:
  flyway:
    enabled: true
    locations: classpath:db/migration
    baseline-on-migrate: true

  datasource:
    url: jdbc:postgresql://localhost:5432/robo_advisor
    username: robo
    password: password
  jpa:
    hibernate:
      ddl-auto: none
```

You can override these settings using environment variables or by providing an external configuration file.

### Running Locally

1. Clone the repository
2. Configure the database connection in `application.yaml`
3. Run the application:
   ```
   ./gradlew bootRun
   ```

### Running with Docker

1. Build the Docker image:
   ```
   docker build -t user-service .
   ```

2. Run the container:
   ```
   docker run -p 8080:8080 -e SPRING_DATASOURCE_URL=jdbc:postgresql://host.docker.internal:5432/robo_advisor user-service
   ```

### Running with Docker Compose

The project includes a `compose.yaml` file for setting up the PostgreSQL database:

```
docker-compose up
```

This will start the PostgreSQL database with the correct configuration. You'll need to run the application separately
using one of the methods described above.

## Database

The service uses PostgreSQL database with the following schema:

### Tables

#### roles

- `id` - Serial primary key
- `role_name` - Unique role name (VARCHAR(50))

#### users

- `id` - Serial primary key
- `username` - Unique username (VARCHAR(50))
- `password` - User password (VARCHAR(255))
- `email` - Unique email address (VARCHAR(100))
- `first_name` - User's first name (VARCHAR(50))
- `last_name` - User's last name (VARCHAR(50))
- `date_of_birth` - User's date of birth (DATE)
- `address` - User's address (TEXT)
- `phone_number` - User's phone number (VARCHAR(20))
- `created_at` - Timestamp when the user was created
- `updated_at` - Timestamp when the user was last updated
- `role_id` - Foreign key to roles table

## Development

### Building the Project

```
./gradlew build
```

### Running Tests

```
./gradlew test
```

## License

[Specify the license here]
