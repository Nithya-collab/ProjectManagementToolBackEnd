#  Project Management Tool — Backend

A production-style **REST API** for a full-featured Project Management Tool, built with **Spring Boot 3** and **Java 21**. It powers project & task management, secure authentication, team collaboration, subscription payments, and automated email notifications.

>  Frontend repo: [ProjectManagementToolFrontEnd](https://github.com/Nithya-collab/ProjectManagementToolFrontEnd)

---

##  Features

-  Secure Authentication & Authorization** — Stateless JWT-based auth with Spring Security, protecting all API routes and supporting role-based access control (Admin / Manager / Member).
-  Project & Task Management** — Create, assign, update, and track projects and tasks through their full lifecycle (To Do → In Progress → Done), enabling Kanban-style workflows.
-  Team & Role Management** — Invite members, assign roles, and manage permissions across projects and teams.
-  Subscription & Payments** — Integrated **Razorpay** payment gateway for handling premium plans / subscription-based access.
-  Automated Email Notifications** — Transactional emails (invites, task updates, payment confirmations) powered by `spring-boot-starter-mail`.
-  Reporting Ready** — Relational schema designed to support dashboards and progress reports on top of project/task data.
-  Clean, Layered Architecture** — Controller → Service → Repository pattern with JPA/Hibernate for maintainable, testable code.

---

##  Tech Stack

| Layer            | Technology                                      |
|------------------|--------------------------------------------------|
| Language         | Java 21                                          |
| Framework        | Spring Boot 3.4.5                                 |
| Security         | Spring Security + JWT (`jjwt`)                    |
| Persistence      | Spring Data JPA / Hibernate                       |
| Database         | MySQL                                             |
| Payments         | Razorpay Java SDK                                 |
| Email            | Spring Boot Starter Mail                          |
| Build Tool       | Maven (with Maven Wrapper)                        |
| Boilerplate      | Lombok                                            |
| Testing          | Spring Boot Test, Spring Security Test            |

---

##  Project Structure

```
ProjectManagementToolBackEnd/
├── src/
│   ├── main/
│   │   ├── java/com/projectManagementTool/  
│   │   └── resources/
│   │       └── application.properties        
│   └── test/                                  
├── .mvn/wrapper/
├── mvnw / mvnw.cmd
├── pom.xml
└── README.md
```

---

##  Getting Started

### Prerequisites

- Java 21+
- Maven (or use the included `mvnw` wrapper)
- MySQL server running locally or remotely
- A Razorpay account (test keys) for payment features
- SMTP credentials (e.g. Gmail App Password) for email features

### 1. Clone the repository

```bash
git clone https://github.com/Nithya-collab/ProjectManagementToolBackEnd.git
cd ProjectManagementToolBackEnd
```

### 2. Configure environment

Create/update `src/main/resources/application.properties` with your own values:

```properties
# Database
spring.datasource.url=jdbc:mysql://localhost:3306/project_management_tool
spring.datasource.username=YOUR_DB_USERNAME
spring.datasource.password=YOUR_DB_PASSWORD
spring.jpa.hibernate.ddl-auto=update

# JWT
jwt.secret=YOUR_JWT_SECRET
jwt.expiration=86400000

# Mail
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=YOUR_EMAIL
spring.mail.password=YOUR_APP_PASSWORD

# Razorpay
razorpay.key.id=YOUR_RAZORPAY_KEY_ID
razorpay.key.secret=YOUR_RAZORPAY_KEY_SECRET
```

>  Never commit real secrets. Use environment variables or a `.env`/secrets manager in production.

### 3. Run the application

```bash
# Using the Maven wrapper
./mvnw spring-boot:run

# or, if Maven is installed globally
mvn spring-boot:run
```

The API will start on `http://localhost:8080` by default.

### 4. Run tests

```bash
./mvnw test
```

---

##  Authentication Flow

1. User registers / logs in → server validates credentials.
2. Server issues a signed **JWT** on successful login.
3. Client sends the JWT in the `Authorization: Bearer <token>` header on subsequent requests.
4. Spring Security filters validate the token and enforce role-based access on protected endpoints.

---

##  Contributing

Contributions, issues, and feature requests are welcome!

1. Fork the repo
2. Create your feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

---

##  License

This project is open source and available for learning and portfolio purposes.

---

##  Author

**Nithya** — [GitHub](https://github.com/Nithya-collab)
