# Restaurant Reservation System (backend)

Reservation system for a restaurant with a filtering functionality and customizable floor planning.

## 0. Content

- [1. Tech Stack](#1-tech-stack)
- [2. Setup](#2-setup)
  - [2.1. Run locally](#21-run-locally)
  - [2.2. Run using docker](#22-run-using-docker)
- [3. Usage](#3-usage)
- [4. Documentation](#4-documentation)
- [5. Features for implementation](#5-features-for-implementation)
    - [5.1. Admin Dashboard](#51-admin-dashboard)
        - [5.1.1. What's currently done?](#511-whats-currently-done)
        - [5.1.2. What should be done?](#512-what-should-be-done)
    - [5.2. Pre-ordering food](#52-pre-ordering-food)
        - [5.2.1. What should be done?](#521-what-should-be-done)
    - [5.3. Session](#53-session)
        - [5.3.1. What should be done?](#531-what-should-be-done)
    - [5.4. Tests](#54-tests)
  

## 1. Tech Stack

- Java 25
- Spring Boot 4
- PostgreSQL
- Flyway

## 2. Setup

First of all, you need to clone a repository using:

1) HTTPS,
```shell
git clone https://github.com/fyberov-dev/Restaurant-Booking-System-Backend.git
```

2) or SSH.
```shell
git clone git@github.com:fyberov-dev/Restaurant-Booking-System-Backend.git
```

### 2.1. Run locally

Build the project

```shell
./gradlew build -x test
```
*currently without tests

Run .jar file

```shell
java -jar build/libs/Backend-0.0.1-SNAPSHOT.jar
```

### 2.2. Run using docker

Find `.env.example` file in a root, alter it and delete ".example" from name of the file, so new name will be `.env`

`.env.example`
```
POSTGRES_DB=
POSTGRES_USERNAME=
POSTGRES_PASSWORD=
```

For example, you can use next credentials (only in dev purposes):

`.env`
```
POSTGRES_DB=postgres
POSTGRES_USERNAME=postgres
POSTGRES_PASSWORD=postgres
```

Build project using gradle

```shell
./gradlew build -x test
```
*currently without tests

Run docker

```
docker compose up --build -d
```

## 3. Usage

Access backend using [WebUI](https://github.com/fyberov-dev/Restaurant-Booking-System-Frontend) or  [Postman](https://www.postman.com/) via http://localhost:8080/

Access http://localhost:8080/api/v1/restaurant to check if it works properly

## 4. Documentation

When backend works use next link to see documentation:

http://localhost:8080/api/swagger-ui.html

## 5. Features for implementation

### 5.1. Admin Dashboard

Admin dashboard to control restaurant data and tables in the restaurant 

#### 5.1.1. What's currently done?

Implemented endpoint to add table in the restaurant.

Currently, it can be used, but only using [Postman](https://www.postman.com/) or [curl](https://curl.se/)

Example request using `curl`:

```shell
curl -X POST http://localhost:8080/api/v1/table \
-H 'Content-Type: application/json' \
-d '{"guests": 2, "x": 100, "y": 50, "isVertical": true}'
```

#### 5.1.2. What should be done?

Need to implement new endpoint for authorization.

After successful registration will be created JWT token for the session.

After each "admin request" token will be validated and checked on an admin role

### 5.2. Pre-ordering food

After table reservation suggest a food to pre-order.

#### 5.2.1. What should be done?

Find API related with the food and use it to suggest to the customer after table reservation.

### 5.3. Session

Customer sessions with an overview of a reserved tables

#### 5.3.1. What should be done?

After table reservation create a JWT token that will be storing reservations of a customer.

So, customer will always have an overview of a reserved tables. Or if customer pre-ordered food he will see a list of pre-ordered food. 

### 5.4. Tests

Test application by writing unit tests