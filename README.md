# Java Banking App

[![CI](https://github.com/Wayrier/java-banking-app/actions/workflows/maven.yml/badge.svg)](https://github.com/Wayrier/java-banking-app/actions/workflows/maven.yml)
![Java](https://img.shields.io/badge/Java-17-orange)
![Maven](https://img.shields.io/badge/Build-Maven-informational)
![Tests](https://img.shields.io/badge/Tests-JUnit%205-blue)
![License: MIT](https://img.shields.io/badge/License-MIT-yellow)

A compact Java CLI application that demonstrates object-oriented design, banking logic, JSON persistence, and automated tests. The app is intentionally small, so the project can be reviewed quickly as part of a portfolio or internship application.

## Features

- Create bank accounts with unique IDs
- Deposit and withdraw money with validation
- Transfer money between accounts
- Store transaction history per account
- Save and load account data from `bank.json`
- Run automated tests with JUnit 5

## Tech Stack

- Java 17
- Maven
- Gson for JSON persistence
- JUnit 5 for tests
- GitHub Actions for CI

## Project Structure

```text
.
|-- pom.xml
|-- src/main/java/com/wayrier/bank/
|   |-- Main.java
|   |-- io/JsonStore.java
|   |-- model/Account.java
|   |-- model/Transaction.java
|   `-- service/BankService.java
|-- src/test/java/com/wayrier/bank/
|   `-- BankServiceTest.java
`-- .github/workflows/maven.yml
```

## Run Locally

Requirements:

- Java 17+
- Maven 3.9+

```bash
mvn -q clean package
mvn -q exec:java
```

The CLI menu provides the main workflow:

```text
1) Create account  2) Deposit  3) Withdraw  4) Transfer
5) List accounts   6) Save     7) Load      0) Exit
```

## Tests

```bash
mvn -q test
```

The GitHub Actions workflow also runs `mvn -B -q clean verify` on push and pull requests.

## Roadmap

- Improve CLI prompts and input handling
- Add more unit tests for edge cases
- Add monthly statements or interest calculation
- Add SQLite or H2 persistence as an alternative to JSON
- Optional: build a simple JavaFX dashboard
