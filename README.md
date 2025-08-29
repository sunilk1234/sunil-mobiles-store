# Spring Boot E-commerce (Mobiles)

**Stack**: Spring Boot 3 (Web, Thymeleaf, Data JPA, Validation), MySQL, Spring Security Crypto (BCrypt).

## Features
- Product list/search, details
- Register/Login (session) with BCrypt password hashing
- Session Cart (add/update/remove)
- Checkout → persists Order + Items
- Simple Admin page for products (create/delete)

## Prerequisites
- JDK 17+
- Maven 3.x
- MySQL 8.x (Workbench optional)
- Eclipse IDE for Enterprise Java (or IntelliJ/VS Code)

## 1) MySQL setup
Create DB (Workbench → Query window):
```sql
SOURCE sql/schema.sql;
CREATE DATABASE IF NOT EXISTS mobilestore;
```
Update `src/main/resources/application.properties` with your MySQL username/password.

## 2) Run
- `mvn spring-boot:run` (or run `Application` in Eclipse)
- Visit `http://localhost:8080/`

## Seed data
`src/main/resources/data.sql` inserts a few mobiles on first run.

## Notes
- No Spring Security login flow is enforced; session auth is custom and minimal for demo.
- Add real authorization (e.g., restrict `/admin/*`) with Spring Security if needed.
