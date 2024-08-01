# Backend System for a Job Application

Project-based learning through [[2024] Java Spring Boot Microservices with k8s, Docker, AWS | Monolithic to Microservices [PART 1]](https://youtu.be/BLlEgtp2_i8?feature=shared).

The job application is responsible for managing jobs.
Jobs will be posted by companies, and these companies will have reviews about their workplaces, typically posted by employees who use the website.

## Summary

- [Technologies](#technologies)
- [Structure](#structure)
  - [Architectural Style](#architectural-style)
  - [Layers](#layers)
- [API Endpoints](#api-endpoints)
  - [Job Microservice](#job-microservice)
  - [Company Microservice](#company-microservice)
  - [Review Microservice](#review-microservice)
- [Built With](#built-with)

## Technologies

- Built `REST APIs` to create endpoints for managing jobs, companies, and reviews resources.
- Defined `JPA (Java Persistence API)` entities and repositories for data management with `Hibernate`, using `H2` for development and `PostgreSQL` for production.
- Containerized the `spring boot` job application and ran it inside a `Docker` container.
- Leveraged `Spring Boot Actuator` endpoints to monitor and manage the job application, including `/health`, `/info`, `/metrics`, `/loggers`, `/beans`, and `/shutdown`.
- Ran `PostgreSQL` and `PGAdmin` inside `Docker` containers, and configured `Docker Networks` to enable communication between them.
- Utilized `Docker Compose` to manage multi-container `Docker` applications.
- Refactored the `monolithic` job application into separate `microservices`, each running on different ports and using separate databases.
- Implements `RESTful` interactions between microservices using `RestTemplate`.
- Applied the `DTO (Data Transfer Object)` Pattern to design the structure of the data exposed through the API.

## Structure

### Architectural Style

We initially built the backend using `monolithic architecture`, and later transformed it into `microservice architecture`.

- **Monolithic Architecture**

  The monolithic form includes `company component`, `review component` and `job component` within a single project, all running on one server.
  Accessing the application from the browser is essentially sending requests to this single server, where the interconnected components interact with a shared database.

  <img width="1440" alt="monolithic1" src="https://github.com/user-attachments/assets/6a205efa-5aeb-48fb-abde-b6543c70ef05">

- **Microservice Architecture**
  
  We refactored the monolithic application into three microservices: `company microservice`, `job microservice`, and `review microservice`, each responsible for its specific functionality.
  Each microservice has its own database, runs on its own server, and can independently receive requests.

  <img width="1440" alt="microservice" src="https://github.com/user-attachments/assets/a6ecd028-af11-4589-8b81-b7b2f94fdd7d">
  
  These microservices are separate `Spring Boot` projects, each with its own dependencies configured.
  For the end user, it will appear as a single application, but on the backend, three microservices will run on different ports:
  
  - Company microservice on port 8081
  - Job microservice on port 8082
  - Review microservice on port 8083

  We established inter-service communication between the `job microservice` and the `company microservice`, using a `Data Transfer Object (DTO)` to encapsulate data from both services.
  When a user calls the API to fetch all jobs from the `job microservice`, the response will include detailed company information alongside each job, instead of just the company ID.
  
  <img width="1440" alt="dto" src="https://github.com/user-attachments/assets/1b315539-d378-4415-b3a3-c74e5d8b5785">

### Layers

Each of the three services (job, company and review) has three main layers: the presentation layer, the service layer, and the data access layer.

- **Presentation Layer**
  
  The presentation layer presents data and application features to the user, essentially acting as the frontend view.
  This layer contains all the `controller` classes, which handle user requests, validate inputs, and pass the data to the service layer.

- **Service Layer**
  
  The service layer contains the business logic of the application. This is where evaluations, decision-making, and data processing occur.
  It interacts with both the presentation and data access layers, serving as an intermediary that communicates between them.

- **Data Access Layer**
  
  The data access layer houses all `repository` classes responsible for database interactions, such as reading, retrieving, and saving data.
  This layer manages access to and operations on the underlying databases, which can be either relational or `NoSQL` databases.

  <img width="1440" alt="monolithic2" src="https://github.com/user-attachments/assets/86c12851-91ea-40cc-a844-fc36cdc92ed3">

## API Endpoints

### Job Microservice
- `GET /jobs` - Retrieve all jobs
- `POST /jobs` - Create a new job
- `GET /jobs/{id}` - Retrieve a specific job
- `PUT /jobs/{id}` - Update a specific job
- `DELETE /jobs/{id}` - Delete a specific job

### Company Microservice
- `GET /companies` - Retrieve all companies
- `POST /companies` - Create a new company
- `GET /companies/{id}` - Retrieve a specific company
- `PUT /companies/{id}` - Update a specific company
- `DELETE /companies/{id}` - Delete a specific company

### Review Microservice
- `GET /reviews?companyId={id}` - Retrieve all reviews for a specific company
- `POST /reviews?companyId={id}` - Create a new review for a specific company
- `GET /reviews/{id}` - Retrieve a specific review
- `PUT /reviews/{id}` - Update a specific review
- `DELETE /reviews/{id}` - Delete a specific review

## Built With
- Spring Boot
- Maven
