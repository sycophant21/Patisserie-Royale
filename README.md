# Patisserie-Royale

> Because your grandma's secret recipe deserves version control.

A **social recipe-sharing web application** built with Spring Boot. Users can create profiles, share dishes and their recipes, and browse what others have cooked up.

## What it does

- Create and manage user accounts
- Add dishes with associated recipes (ingredients, steps)
- Browse and discover recipes shared by other users
- DAO-pattern backend for clean separation of data access and business logic

## How it works

The application follows a layered architecture:
- **Domain layer**: `Dish`, `Recipe`, and `User` are the core entities, each with typed ID wrappers (`DishID`, `RecipeID`)
- **DAO layer**: `DishDAO` and `UserDAO` handle all database interactions using JDBC/JPA
- **Entry point**: `Main.java` bootstraps the Spring context

Recipes are linked to dishes through a composition relationship — a `Dish` can have one or more `Recipe` variants, each describing ingredients and preparation steps.

## Tech stack

- **Java**
- **Spring Boot**
- **Maven**
- **JDBC / JPA** for persistence

## Getting started

### Prerequisites

- Java 11+
- Maven 3.6+
- A running database (MySQL or H2 for local dev)

### Configure

Update the database connection settings in `RecipeApp/src/main/resources/application.properties` (or equivalent config class) with your DB credentials.

### Run

```bash
cd RecipeApp
mvn spring-boot:run
```

### Build

```bash
cd RecipeApp
mvn clean package
java -jar target/RecipeApp-*.jar
```

## Project structure

```
RecipeApp/src/main/java/com/
├── dao/
│   ├── DishDAO.java      # CRUD for dishes
│   ├── UserDAO.java      # CRUD for users
│   └── Main.java         # Spring Boot entry point
└── domain/
    ├── classes/
    │   ├── Dish.java     # dish entity
    │   ├── Recipe.java   # recipe entity (linked to dish)
    │   └── User.java     # user entity
    └── id/
        ├── DishID.java
        └── RecipeID.java
```
