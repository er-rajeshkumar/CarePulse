# CarePulse

CarePulse is a hospital and patient management system focused on improving patient care through medicine reminders, treatment tracking, follow-up management, and future automated patient notifications.

The project is being developed using Java and Spring Boot with a focus on learning and implementing enterprise-level backend architecture.

## Project Goals

CarePulse aims to help hospitals and healthcare providers manage:

* Patients
* Doctors
* Hospitals
* Patient treatment cases
* Medicines and prescriptions
* Medicine reminders
* Medicine intake tracking
* Follow-up appointments
* Family members and emergency contacts
* Patient notifications
* Application and audit logging

The primary focus of the project is improving medicine adherence and ensuring patients do not miss medicines or follow-up appointments.

## Current Features

### Patient Management

* Add a patient
* Search patient by ID
* Retrieve all patients
* Update patient information
* Patient validation
* Patient not-found exception handling
* Global exception handling

### Backend Architecture

The application follows a layered architecture:

```text
Controller
    ↓
Service
    ↓
Repository
    ↓
Entity
    ↓
MySQL Database
```

The project also uses DTOs for API request and response handling.

### Logging

Application logging is configured to maintain logs of important application events, such as:

* Patient creation
* Patient search
* Patient updates
* Errors and exceptions
* Other application events

Future versions may include detailed audit logging containing information about which user performed an operation.

## Database

CarePulse currently uses MySQL.

Main database entities include:

* HOSPITAL
* SPECIALIZATION_MASTER
* DOCTOR
* HOSPITAL_DOCTOR
* PATIENT
* HOSPITAL_PATIENT
* FAMILY_MEMBER
* PATIENT_CASE
* MEDICINE
* MEDICATION
* REMINDER
* MEDICINE_TAKE
* FOLLOW_UP
* MESSAGE_HISTORY

## Technology Stack

### Backend

* Java 17
* Spring Boot 3.1.7
* Spring Web
* Spring Data JPA
* Hibernate
* Maven

### Database

* MySQL 8

### Development Tools

* Spring Tool Suite (STS)
* Eclipse
* Git
* GitHub

## Project Structure

```text
src/main/java
└── com.carepulse
    ├── CarePulseApplication.java
    │
    ├── controller
    │   ├── TestController.java
    │   └── PatientController.java
    │
    ├── service
    │   └── PatientService.java
    │
    ├── repository
    │   └── PatientRepository.java
    │
    ├── entity
    │   ├── Patient.java
    │   └── Sex.java
    │
    ├── dto
    │   ├── PatientCreateRequestDto.java
    │   └── PatientResponseDto.java
    │
    ├── exception
    │   ├── PatientNotFoundException.java
    │   └── GlobalExceptionHandler.java
    │
    └── config
```

## API Examples

### Test API

```http
GET /api/test
```

### Get All Patients

```http
GET /api/patients
```

### Get Patient by ID

```http
GET /api/patients/{id}
```

Example:

```http
GET /api/patients/1
```

### Add Patient

```http
POST /api/addPatient
```

Example request:

```json
{
    "firstName": "Rajesh",
    "middleName": "H",
    "lastName": "Kumar",
    "sex": "MALE",
    "email": "rajesh@gmail.com",
    "phone": "9876543210",
    "address": "Lucknow"
}
```

### Update Patient

```http
PUT /api/patients/{id}
```

## Error Handling

CarePulse uses custom exceptions and global exception handling.

For example, when a patient does not exist:

```json
{
    "message": "Patient not found with id: 999"
}
```

The API returns:

```http
404 NOT_FOUND
```

Validation errors are also handled through Spring's validation framework.

## Configuration

Database configuration is maintained in:

```text
src/main/resources/application.properties
```

Example:

```properties
spring.application.name=carepulse
server.port=8080

spring.datasource.url=jdbc:mysql://localhost:3306/carepulse
spring.datasource.username=root
spring.datasource.password=YOUR_PASSWORD

spring.jpa.hibernate.ddl-auto=validate
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

logging.file.name=logs/carepulse.log
logging.level.root=INFO
logging.level.com.carepulse=DEBUG
```

Do not commit real database passwords, API keys, access tokens, or other secrets to GitHub.

## Future Plans

Planned features include:

* Complete Doctor Management
* Hospital Management
* Patient-Hospital relationship management
* Patient Case Management
* Medicine Management
* Medication/Prescription Management
* Medicine Reminder Scheduling
* Medicine Take/Adherence Tracking
* Follow-up Appointment Management
* Family Member Management
* WhatsApp Notifications
* SMS/Email/Push Notifications
* Notification History
* User Authentication
* Role-Based Access Control
* JWT Security
* Audit Logging
* Admin Dashboard
* Doctor Dashboard
* Hospital Dashboard
* Frontend application
* Online Doctor Consultation
* Reporting and analytics


## Project Status

🚧 **Under Active Development**

The project is being developed incrementally, with new modules and features being added over time.

## Author

Rajesh Kumar
