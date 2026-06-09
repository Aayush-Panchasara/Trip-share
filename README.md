# Trip-Share - Expense Splitting Application (Total time taken = 3 hours)

Trip-Share is a Spring Boot REST API application designed to help groups of people manage and split expenses during trips. It allows users to create groups, add expenses, and automatically calculate how much each person owes.

## Project Overview

Trip-Share simplifies expense management for group trips by:

- Managing user profiles
- Creating expense groups (e.g., trip groups)
- Recording shared expenses
- Automatically calculating individual expense shares
- Tracking who paid for what and who owes whom

## Core Functionality

### 1. User Management

- **Create User** - Register a new user
- **Get All Users** - Retrieve all registered users
- **Get User by ID** - Fetch a specific user's details

### 2. Group Management

- **Create Group** - Create a new expense group with a creator/owner
- **Get All Groups** - Retrieve all expense groups
- **Get Group by ID** - Fetch a specific group's details
- **Add Member to Group** - Add a user to an expense group

### 3. Expense Management

- **Create Expense** - Record a new expense in a group (splits cost among participants)
- **Get All Expenses** - Retrieve all expenses across all groups
- **Get Expense by ID** - Fetch a specific expense's details

### 4. Expense Sharing

- Automatic calculation of expense shares
- Equal split among all participants
- Tracks who paid for the expense
- Stores individual share amounts for settlement

## API Endpoints

### User Endpoints

| Method | Endpoint              | Description       |
| ------ | --------------------- | ----------------- |
| POST   | `/api/users`          | Create a new user |
| GET    | `/api/users`          | Get all users     |
| GET    | `/api/users/{userId}` | Get user by ID    |

### Group Endpoints

| Method | Endpoint                        | Description         |
| ------ | ------------------------------- | ------------------- |
| POST   | `/api/groups`                   | Create a new group  |
| GET    | `/api/groups`                   | Get all groups      |
| GET    | `/api/groups/{groupId}`         | Get group by ID     |
| POST   | `/api/groups/{groupId}/members` | Add member to group |

### Expense Endpoints

| Method | Endpoint                    | Description          |
| ------ | --------------------------- | -------------------- |
| POST   | `/api/expenses`             | Create a new expense |
| GET    | `/api/expenses`             | Get all expenses     |
| GET    | `/api/expenses/{expenseId}` | Get expense by ID    |

## Technologies Used

- **Framework**: Spring Boot
- **Language**: Java
- **Database**: JPA/Hibernate with relational database
- **Build Tool**: Maven
- **Dependencies**: Lombok, Spring Web, Spring Data JPA
- **AI Tool**: Codex

## Project Structure

```
trip-share/
├── src/
│   ├── main/
│   │   ├── java/com/example/trip_share/
│   │   │   ├── controller/          # REST API Controllers
│   │   │   ├── service/             # Business Logic Services
│   │   │   ├── entity/              # JPA Entities
│   │   │   ├── repository/          # Data Access Layer
│   │   │   ├── dto/                 # Data Transfer Objects
│   │   │   └── TripShareApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
├── pom.xml
├── mvnw
├── mvnw.cmd
└── README.md
```

## Key Entities

### User

- Stores user information (name, email)
- Can be a group creator or member

### ExpenseGroup

- Represents a trip or expense group
- Has a creator/owner
- Contains multiple members

### GroupMember

- Represents the relationship between users and groups
- Tracks group membership

### Expense

- Records individual expenses in a group
- Tracks who paid and the total amount

### ExpenseShare

- Tracks each participant's share of an expense
- Calculates individual amounts owed

## Running the Application

### Prerequisites

- Java 11 or higher
- Maven

### Build and Run

```bash
# Build the project
./mvnw clean build

# Run the application
./mvnw spring-boot:run
```

The application will start on `http://localhost:8080`

## Features Highlights

- **Lean Response DTOs** - Returns only essential information to reduce payload size
- **Automatic Expense Calculation** - Splits expenses equally among participants
- **Transaction Management** - Ensures data consistency with transactional operations
- **RESTful API Design** - Clean and intuitive endpoint structure
