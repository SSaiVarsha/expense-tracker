# Expense Tracker - Java REST API

A backend Expense Tracker application developed using **Java, Spring Boot, REST APIs and MySQL**. The application allows users to register and log in, record income and expenses, and perform CRUD operations on transaction records.

## Features

- User registration and login
- BCrypt password hashing
- Add income and expense records
- View transactions by user
- Update transaction details
- Delete transactions
- MySQL database persistence
- RESTful API endpoints
- Input validation for transaction type and amount

## Tech Stack

| Technology | Purpose |
|---|---|
| Java 17 | Application development |
| Spring Boot | Backend framework |
| Spring Web | REST API development |
| Spring Data JPA | Database operations |
| Spring Security | Password encryption |
| MySQL | Relational database |
| Maven | Dependency management |
| Postman | API testing |

## Architecture

```text
Client / Postman
       |
       v
REST Controllers
       |
       v
Spring Data JPA Repositories
       |
       v
MySQL Database
```

## Project Structure

```text
expense-tracker-java/
├── database/
│   └── schema.sql
├── src/
│   └── main/
│       ├── java/com/sai/expensetracker/
│       │   ├── config/
│       │   │   └── SecurityConfig.java
│       │   ├── controller/
│       │   │   ├── AuthController.java
│       │   │   └── TransactionController.java
│       │   ├── model/
│       │   │   ├── User.java
│       │   │   └── Transaction.java
│       │   ├── repository/
│       │   │   ├── UserRepository.java
│       │   │   └── TransactionRepository.java
│       │   └── ExpenseTrackerApplication.java
│       └── resources/
│           └── application.properties
├── .gitignore
├── pom.xml
└── README.md
```

## Database Setup

1. Install and start MySQL.
2. Open MySQL Workbench or the MySQL command line.
3. Run the SQL in:

```text
database/schema.sql
```

This creates the `expense_tracker` database and its `users` and `transactions` tables.

## Configuration

Open:

```text
src/main/resources/application.properties
```

Change:

```properties
spring.datasource.username=root
spring.datasource.password=YOUR_MYSQL_PASSWORD
```

to match your local MySQL credentials.

**Do not upload your real database password to GitHub.**

## Run the Application

Make sure Java 17, Maven and MySQL are installed.

```bash
mvn clean install
mvn spring-boot:run
```

The application starts at:

```text
http://localhost:8080
```

## REST API Endpoints

### 1. Register User

**POST** `/api/auth/register`

Request:

```json
{
  "username": "varsha",
  "password": "123456"
}
```

### 2. Login

**POST** `/api/auth/login`

Request:

```json
{
  "username": "varsha",
  "password": "123456"
}
```

### 3. Add Expense

**POST** `/api/transactions/user/1`

Request:

```json
{
  "type": "EXPENSE",
  "amount": 250.00,
  "category": "Food",
  "description": "Lunch",
  "date": "2026-08-26"
}
```

### 4. Add Income

**POST** `/api/transactions/user/1`

Request:

```json
{
  "type": "INCOME",
  "amount": 5000.00,
  "category": "Salary",
  "description": "Monthly income",
  "date": "2026-08-26"
}
```

### 5. Get User Transactions

**GET** `/api/transactions/user/1`

### 6. Update Transaction

**PUT** `/api/transactions/1`

Request:

```json
{
  "type": "EXPENSE",
  "amount": 300.00,
  "category": "Food",
  "description": "Dinner",
  "date": "2026-08-26"
}
```

### 7. Delete Transaction

**DELETE** `/api/transactions/1`

## Testing with Postman

Recommended testing sequence:

```text
1. Register
2. Login
3. Add income
4. Add expense
5. Get transactions
6. Update transaction
7. Delete transaction
```

## Important Security Note

This project hashes passwords using BCrypt. The sample configuration permits the REST endpoints for development and demonstration purposes. For production deployment, JWT-based authentication and authorization should be added.

## Future Enhancements

- JWT authentication and role-based authorization
- Monthly income and expense summaries
- Category-wise expense reports
- Search and filtering
- Pagination
- React.js frontend
- Unit and integration tests
- Swagger/OpenAPI documentation
- Docker deployment

## Author

**Sai Varsha S.**

Integrated M.Tech Software Engineering  
Vellore Institute of Technology
