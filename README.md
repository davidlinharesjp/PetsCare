# PetsCare

PetsCare is a Spring Boot API for managing pets, users, orders and products. The project uses Maven and provides different profiles for development, production and tests.

## Building

Use the Maven wrapper to build the application:

```bash
./mvnw clean package
```

This command compiles the sources and packages the application into a JAR located in `target/`.

## Running

The active profile controls which configuration is loaded. Profiles can be passed using the `spring.profiles.active` property.

### Development

Runs the application with the `dev` profile which enables a local PostgreSQL database and disables security restrictions:

```bash
./mvnw spring-boot:run -Dspring-boot.run.profiles=dev
```

### Production

The default profile is `prod`. It requires authentication on most endpoints and connects to the PostgreSQL database defined in `application-prod.properties`:

```bash
./mvnw spring-boot:run    # uses prod profile from application.properties
```

### Test

The `test` profile uses an in‑memory H2 database and is useful for running automated tests:

```bash
./mvnw spring-boot:run -Dspring-boot.run.profiles=test
```

## Required environment variables

Some settings are read from the environment or from the corresponding `application-*.properties` file:

- `jwt.secret` – secret key used to sign JWT tokens.
- `jwt.expiration` – expiration time for generated tokens in milliseconds.
- `file.disco.raiz` – base URL used when storing uploaded files.

These values can be set via environment variables or overridden in the appropriate properties file for each profile.

## Testing

Execute the unit tests with:

```bash
./mvnw test
```

The test profile (`test`) is automatically used when running the tests.

## Example API usage

Authenticate and obtain a token:

```bash
curl -X POST http://localhost:8080/auth \
  -H "Content-Type: application/json" \
  -d '{"email":"user@example.com","key_password":"secret"}'
```

Listing all pets:

```bash
curl http://localhost:8080/pets/findAll
```

Registering a new user:

```bash
curl -X POST http://localhost:8080/user/register \
  -H "Content-Type: application/json" \
  -d '{"name":"John","email":"john@example.com","key_password":"secret"}'
```

Use the returned JWT token in the `Authorization` header (`Bearer <token>`) when accessing secured endpoints in the production profile.

