# 🔐 Spring Boot JWT Authentication & Customer Management API

A secure backend REST API built with **Spring Boot, Spring Security, JWT, and MySQL**, demonstrating real-world authentication, authorization, and CRUD operations using clean architecture principles.

---

## 🚀 Tech Stack

* Java 17
* Spring Boot 3+
* Spring Security (JWT Authentication)
* Spring Data JPA / Hibernate
* MySQL
* Lombok
* ModelMapper
* Maven

---

## 📌 Key Features

### 🔐 Authentication & Security

* JWT-based authentication (Access Token + Refresh Token)
* Stateless session management
* Custom JWT filter for request validation
* Role-based authorization (ROLE_USER, ROLE_ADMIN)
* Password encryption using BCrypt

### 👤 User Management

* User registration (Sign Up)
* Login and token generation
* Refresh token mechanism
* Paginated user listing (Admin only)

### 🧾 Customer Management

* Create customer records
* Fetch paginated customer list
* DTO-based request/response handling
* Duplicate email validation

### 🧱 Architecture

* Layered architecture (Controller → Service → Repository)
* DTO pattern for clean API design
* Entity relationship mapping using JPA
* Centralized response structure

---

## 🧠 System Architecture

```
Client → Controller → Service → Repository → Database
                ↓
        JWT Filter (Security Layer)
```

* Every request is validated using JWT Filter
* Authentication is stateless
* Role-based access control enforced via Spring Security

---

## 🔐 Authentication Flow

1. User registers (sign-up)
2. User logs in with username & password
3. System validates credentials
4. Generates:

    * Access Token (short-lived)
    * Refresh Token (long-lived)
5. Client sends token in header:

   ```
   Authorization: Bearer <access_token>
   ```
6. JWT Filter validates token on every request
7. Refresh token used to generate new access token

---

## 📡 API Endpoints

### 🔑 Authentication APIs

| Method | Endpoint                 | Description             |
| ------ | ------------------------ | ----------------------- |
| POST   | `/api/v1/login/getToken` | Login & generate tokens |
| POST   | `/api/v1/login/refresh`  | Refresh access token    |
| POST   | `/users/api/v1/signUp`   | Register new user       |

---

### 👤 User APIs (Admin Only)

| Method | Endpoint                                |
| ------ | --------------------------------------- |
| GET    | `/users/api/v1/userList?page=0&size=10` |

---

### 🧾 Customer APIs

| Method | Endpoint                                |
| ------ | --------------------------------------- |
| POST   | `/customers/api/v1/save`                |
| GET    | `/customers/api/v1/list?page=0&size=10` |

---

## 📦 Example Requests

### 🔹 User Sign Up

```json
{
  "userName": "john",
  "password": "123456",
  "email": "john@example.com",
  "role": "ROLE_USER"
}
```

---

### 🔹 Login Request

```
POST /api/v1/login/getToken?userName=john&password=123456
```

---

### 🔹 Response Example

```json
{
  "code": 200,
  "data": {
    "accessToken": "eyJhbGciOiJIUzI1NiIs...",
    "refreshToken": "c8f1a3d2-xxxx-xxxx"
  },
  "messages": "Success"
}
```

---

## 🧾 Database Entities

* Users
* Customers
* Orders
* OrderItems
* Items
* RefreshToken

---

## 🔐 Security Highlights

* Spring Security filter chain customization
* JWT validation on every request
* Role-based authorization (`@EnableMethodSecurity`)
* Secure password storage using BCrypt
* Stateless API design (no session storage)

---

## 📈 Pagination Support

* Users API supports pagination
* Customers API supports pagination
* Built using Spring Data `Pageable`

---

## 🧠 Concepts Demonstrated

* JWT Authentication & Authorization
* Spring Security Filter Chain
* Role-based access control
* REST API design best practices
* DTO pattern implementation
* Entity relationships (OneToMany, ManyToOne)
* Global response structure
* Exception handling basics

---

## 🔮 Future Enhancements

* Add Swagger/OpenAPI documentation
* Dockerize application
* Add Redis for token storage optimization
* Add unit & integration tests
* Improve exception handling with global handler
* Add audit logs

---

## 👨‍💻 Author

Java Backend Developer | Spring Boot | REST APIs | JWT Security
Freelance Engineer (Upwork)

---

## 📌 Project Purpose

This project demonstrates backend engineering skills including secure AP
