# ecommerce-back-end

## Description
Spring Boot backend for an e-commerce application. Provides REST APIs for products, orders, users, and image uploads (S3), backed by PostgreSQL with JPA/Hibernate.

## Dependencies
- Java 21
- Maven (via mvnw)
- Spring Boot 4.0.1
- Spring Web
- Spring Data JPA
- PostgreSQL JDBC driver
- AWS Java SDK for S3
- Spring Boot Test (tests)

## Local setup
1. Install Java 21 and ensure `java -version` returns 21.
2. Ensure PostgreSQL is running locally and create a database (e.g., `ecommerce`).
3. Update [src/main/resources/application.properties](src/main/resources/application.properties) with your local settings, or use environment variables for secrets. Suggested values:
	- `spring.datasource.url=jdbc:postgresql://localhost:5432/ecommerce`
	- `spring.datasource.username=YOUR_DB_USER`
	- `spring.datasource.password=YOUR_DB_PASSWORD`
	- `aws.s3.bucket-name=YOUR_BUCKET`
	- `aws.s3.region=YOUR_REGION`
	- `aws.access.key.id=YOUR_ACCESS_KEY`
	- `aws.secret.access.key=YOUR_SECRET_KEY`
4. From the project root, run the app:
	- `./mvnw spring-boot:run`
5. The API will be available at http://localhost:5000 (or the `server.port` you configure). 
