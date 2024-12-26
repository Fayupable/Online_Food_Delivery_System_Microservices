# Online Food Delivery Project

## Overview

This project is a microservices-based application designed to manage orders, users, restaurants, and payments. It uses Spring Boot for building the microservices and Maven for dependency management and build automation. The project also integrates with Kafka for messaging and MongoDB and PostgreSQL for data storage.

## Microservices

### Order Service
- **Purpose:** Manages orders, including creation, updating, and retrieval of order details.
- **Database:** PostgreSQL
- **Port:** 8070

### User Service
- **Purpose:** Manages user information, including registration, authentication, and user profile management.
- **Database:** MongoDB
- **Port:** 8050

### Restaurant Service
- **Purpose:** Manages restaurant information, including menu items, restaurant details, and reviews.
- **Database:** MongoDB
- **Port:** 8060

### Payment Service
- **Purpose:** Handles payment processing for orders.
- **Port:** 8222

### API Gateway
- **Purpose:** Routes requests to the appropriate service and provides load balancing.
- **Port:** 8080

## Technologies Used

- **Spring Boot:** Framework for building microservices.
- **Spring Cloud Gateway:** API Gateway for routing and load balancing.
- **Spring Data JPA:** For database interactions with PostgreSQL.
- **Spring Data MongoDB:** For database interactions with MongoDB.
- **Spring Cloud OpenFeign:** For declarative REST client.
- **Spring Kafka:** For Kafka messaging.
- **PostgreSQL:** Database for Order Service.
- **MongoDB:** Database for User and Restaurant Services.
- **Maven:** For dependency management and build automation.

## Configuration

The project uses `application.yml` and `user-service.yml` files for configuration, specifying database connections, server ports, and other settings.

## Running the Application

1. **Clone the repository:**
   ```sh
   git clone https://github.com/Fayupable/order-service.git
   cd order-service
   ```

2. **Build the project:**
   ```sh
   mvn clean install
   ```

3. **Run the services:**
   ```sh
   mvn spring-boot:run -pl order-service
   mvn spring-boot:run -pl user-service
   mvn spring-boot:run -pl restaurant-service
   mvn spring-boot:run -pl payment-service
   mvn spring-boot:run -pl api-gateway
   ```