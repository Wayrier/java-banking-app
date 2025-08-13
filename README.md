# Java Banking App

![Java](https://img.shields.io/badge/Java-17-orange)
![Maven](https://img.shields.io/badge/Build-Maven-informational)
![Tests](https://img.shields.io/badge/Tests-JUnit%205-blue)
![License: MIT](https://img.shields.io/badge/License-MIT-yellow)

A compact Java project that demonstrates **OOP**, basic banking logic, **JSON** persistence (Gson), and a **CLI** menu.  
Great as a Praktikum-ready portfolio project.

## ✨ Features
- Create accounts (UUID)
- Deposit / Withdraw (validation)
- Transfer between accounts
- Transaction history per account
- Save/Load data to `bank.json`

## 🧱 Tech
Java 17 • Maven • Gson • JUnit 5

## 🚀 Run locally
```bash
mvn -q clean package
mvn -q exec:java
🖥️ Usage
mathematica
Copy
Edit
1) Create account  2) Deposit  3) Withdraw  4) Transfer
5) List accounts   6) Save     7) Load      0) Exit
✅ Tests
bash
Copy
Edit
mvn -q test
🗺️ Roadmap
Input validation & nicer prompts

JavaFX GUI (optional)

SQLite/H2 persistence

GitHub Actions CI
