# FinPulse Project

## Overview

FinPulse is a robust financial services platform that manages user accounts, roles, and trades, integrating market data for real-time insights. The application provides a secure, role-based access system and a foundation for building advanced financial services. It is built using **Java 17**, **Spring Boot**, **PostgreSQL**, and **Maven**.

The core components of the application include:

- **User Management**: Registration, login, and role-based access control.
- **Account Management**: Users can manage multiple accounts and monitor their balances.
- **Trading**: Users can place trades in different financial instruments (stocks, bonds, etc.).
- **Market Data**: Provides real-time data for financial instruments like prices and volumes.

## Key Features

### User Management
- **Registration**: New users can register with a unique username, email, and password. On registration, a default "USER" role is assigned, and an "ACTIVE" status is set.
- **Authentication**: Users can log in using their username and password. Authentication is handled securely, with support for basic form login.
- **Role-Based Access Control (RBAC)**: Users are assigned roles (e.g., `USER`, `ADMIN`) that define their access to various endpoints. Administrators have full access to all operations, while regular users have limited access to their personal data.
- **Status Management**: Users' accounts can have a status like "ACTIVE," "SUSPENDED," or "INACTIVE." This allows easy management of user access to the platform.

### Account Management
- **Account Creation**: Each user can have one or more accounts linked to their profile. Each account has a balance and an account type (e.g., "Checking," "Savings").
- **Balance Management**: Account balances are tracked and updated when trades or transactions are made.

### Trading
- **Trade Execution**: Users can place trades in different financial instruments. Each trade is associated with a user and an instrument (e.g., stocks, bonds).
- **Real-Time Tracking**: Trades are logged with timestamps, allowing the platform to track past trades, including quantity, price, and trade type (buy/sell).

### Market Data
- **Instrument Tracking**: Real-time data is gathered for various financial instruments, providing updated prices and volumes.
- **Market Trends**: The platform can store and analyze market data trends over time, helping users make informed trading decisions.

## Technologies Used

- **Java 17**: The project is developed using Java 17, offering modern language features and performance improvements over previous versions.
- **Spring Boot**: A powerful framework for developing stand-alone Java applications, Spring Boot simplifies application configuration and development.
- **PostgreSQL**: A relational database system for storing user, account, trade, and market data. PostgreSQL ensures data integrity and scalability.
- **Maven 3.8.7**: Maven is used for managing project dependencies and the build lifecycle.

## Prerequisites

Before running the FinPulse project, make sure you have the following tools installed:

1. **Java 17**: Install JDK 17. Verify installation with:
   ```bash
   java -version
