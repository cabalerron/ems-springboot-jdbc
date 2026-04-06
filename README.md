# 🚀 Employee Management System (EMS) API

A RESTful API built using **Spring Boot**, **JDBC**, and **PostgreSQL** for managing employee data. This project demonstrates clean architecture using **Controller, Service, Repository (Interface + Implementation), DTO, and Entity layers**.

---

## 📌 Features

* ✅ Add Employee
* ✅ Update Employee
* ✅ Soft Delete (INACTIVE status)
* ✅ Get All Employees (Pagination)
* ✅ Search Employees
* ✅ Filter by Department
* ✅ Filter by Status
* ✅ Email Uniqueness Validation
* ✅ Input Validation

---

## 🛠️ Tech Stack

* **Backend:** Java, Spring Boot
* **Database:** PostgreSQL
* **Data Access:** JDBC Template
* **Build Tool:** Maven
* **Utilities:** Lombok

---

## 📂 Project Structure

```
com.example.ems
│
├── controller
├── service
│     └── impl
├── repository
│     └── impl
├── dto
├── entity
├── exception
└── config
```

---

## 🔗 API Endpoints

| Method | Endpoint                                           | Description          |
| ------ | -------------------------------------------------- | -------------------- |
| POST   | /api/employees                                     | Add employee         |
| PUT    | /api/employees/{id}                                | Update employee      |
| DELETE | /api/employees/{id}                                | Soft delete          |
| GET    | /api/employees?page=0&size=10                      | Get all (paginated)  |
| GET    | /api/employees/search?keyword=abc&page=0&size=10   | Search               |
| GET    | /api/employees/department?dept=IT&page=0&size=10   | Filter by department |
| GET    | /api/employees/status?status=ACTIVE&page=0&size=10 | Filter by status     |

---

## 🧪 Sample Request

```json
{
  "firstName": "John",
  "lastName": "Doe",
  "email": "john.doe@example.com",
  "department": "IT",
  "status": "ACTIVE"
}
```

---

## ⚙️ Setup Instructions

### 1. Clone the Repository

```
git clone https://github.com/cabalerron/ems-springboot-jdbc.git
cd ems-springboot-jdbc
```

### 2. Configure Database

Update `application.properties`:

```
spring.datasource.url=jdbc:postgresql://localhost:5432/ems_db
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.datasource.driver-class-name=org.postgresql.Driver
```

### 3. Run the Application

```
mvn spring-boot:run
```

---

## 🗄️ Database Schema

```sql
CREATE TABLE employees (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(100),
    last_name VARCHAR(100),
    email VARCHAR(150) UNIQUE,
    department VARCHAR(50),
    status VARCHAR(20)
);
```

---

## 👨‍💻 Author

**SystaXErron**

---

## 📌 Notes

* Uses **JDBC (not JPA)** for SQL control
* Implements **clean architecture (interface + implementation)**
* Designed for learning and backend portfolio projects

---

🔥 This project is a solid backend foundation for enterprise-level applications.
