# Restaurant Reservation System (backend)

Reservation system for a restaurant with a filtering functionality and customizable floor planning.

## Setup

First of all, you need to clone a repository using:

1) HTTPS,
```shell
git clone https://github.com/fyberov-dev/Restaurant-Booking-System-Backend.git
```

2) or SSH.
```shell
git clone git@github.com:fyberov-dev/Restaurant-Booking-System-Backend.git
```

### Run locally

Build the project

```shell
./gradlew build -x test
```
*currently without tests

Run .jar file

```shell
java -jar build/libs/Backend-0.0.1-SNAPSHOT.jar
```

### Run using docker

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

## Usage

Access backend using [WebUI](https://github.com/fyberov-dev/Restaurant-Booking-System-Frontend) or  [Postman](https://www.postman.com/) via http://localhost:8080/

Access http://localhost:8080/api/v1/restaurant to check if it works properly