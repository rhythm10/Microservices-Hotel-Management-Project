# Microservices Architecture with Eureka and API Gateway

This repository contains three microservices (User Service, Hotel Service, Rating Service) with their respective databases, integrated with Eureka for service discovery and an API Gateway for routing requests. Additionally, it includes a Rate Limiter, Circuit Breaker, and Spring Security for enhanced functionality and resilience.

## Table of Contents

- [Services Overview](#services-overview)
- [Architecture](#architecture)
- [Prerequisites](#prerequisites)
  
## Services Overview

1. **User Service**
   - **Database**: MySQL
   - Manages user information such as user profiles, ratings provided.

2. **Hotel Service**
   - **Database**: Postgres
   - Manages hotel information including hotel details, ratings.

3. **Rating Service**
   - **Database**: MongoDB
   - Handles ratings and reviews for hotels provided by users.

## Architecture

- **Eureka Registry**: Service registry for service discovery. Each microservice registers with Eureka on startup.
- **API Gateway**: Acts as a single entry point for client requests, routing them to the appropriate microservice.
- **Rate Limiter**: Controls the rate of requests that can be processed by the services to prevent overloading and ensure fair usage.
- **Circuit Breaker**: Provides fault tolerance by automatically redirecting requests and providing fallback mechanisms when a service is down or under heavy load.
- **Spring Security**: Secures the microservices by implementing authentication and authorization mechanisms.


### Prerequisites

- Java 11+
- Maven
- Spring Boot
- MySQL, Postgres, and MongoDB instances

**This setup ensures a robust, scalable, and secure microservices architecture, capable of handling varying loads and maintaining service availability and security.**


