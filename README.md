# Student Management System

A simple RESTful API built with Spring Boot and PostgreSQL for managing student information.

## Tech Stack

- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA
- Hibernate
- PostgreSQL
- Maven

## Features

- Create a student
- Get a student by ID
- Get all students
- Update a student

## REST APIs

| Method | Endpoint | Description |
|---|---|---|
| POST | /api/students | Create a student |
| GET | /api/students/{id} | Get student by ID |
| GET | /api/students | Get all students |
| PUT | /api/students/{id} | Update student |

## Database Configuration

Create a PostgreSQL database:

```sql
CREATE DATABASE student_db;