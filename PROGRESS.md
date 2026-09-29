# CarePulse Backend: Files and Progress

**Snapshot:** 2026-09-28  
**Status:** Active development

This document records the files in the repository and the work visible in the code today. The inventory covers **116 Git-tracked project files** at the time of this snapshot; `PROGRESS.md` is the new document added separately. This is a repository inventory, not proof of who personally authored each file. Generated files in `target/`, runtime files in `logs/`, and the ignored local-only `src/main/resources/application-local.properties` are not counted.

## Progress so far

- Built a Java 17 / Spring Boot 3.1.7 REST backend using Maven, Spring Web, Spring Data JPA, Hibernate, and MySQL. The application is organized into controllers, services, repositories, entities, DTOs, and exception handlers.
- Added APIs for patients, doctors, hospitals, specializations, and hospital-to-patient/doctor relationships. Patient deletion changes status rather than removing the record.
- Added treatment case and follow-up APIs, medicine catalog and medication/prescription APIs, plus reminder and medicine-taking record APIs. The latter store schedules and activity; they do **not** yet send notifications.
- Added request validation and a global exception handler for validation failures and selected domain/general exceptions. Response DTOs provide separate API response shapes, including detailed relationship views.
- Added service/SQL logging configuration, a Dockerfile, environment-based datasource configuration, and a local landing page with API exploration/check pages. These pages are developer aids, not a finished patient-facing frontend.
- Wrote `README.md` with endpoint, request, response, setup, and known-issue documentation.
- Added two test files: `PatientServiceTest.java` checks the active-patient list using a mocked repository (and contains one empty test method); `TestControllerTest.java` checks the health-check message. Broader automated tests remain to be written.

## Files created in this project

Each directory heading is a path relative to the repository root; the names beneath it are files in that directory. This list describes the tracked project files, whether or not they are currently being edited.

### Repository root (4 tracked files)

```text
.gitignore
Dockerfile
pom.xml
README.md
```

`PROGRESS.md` (this file) was added after that inventory.

### `src/main/java/com/carepulse/` (1 file)

```text
CarePulseApplication.java
```

### `src/main/java/com/carepulse/aspect/` (1 file)

```text
ServiceSqlLoggingAspect.java
```

### `src/main/java/com/carepulse/controller/` (14 files)

```text
DoctorController.java
FollowUpController.java
HomeController.java
HospitalController.java
HospitalDoctorController.java
HospitalPatientController.java
MedicationController.java
MedicineController.java
MedicineTakeController.java
PatientCaseController.java
PatientController.java
ReminderController.java
SpecializationController.java
TestController.java
```

### `src/main/java/com/carepulse/dto/` (25 files)

```text
DoctorCreateRequestDto.java
DoctorResponseDto.java
FollowUpCreateRequestDto.java
FollowUpDetailedResponseDto.java
HospitalCreateRequestDto.java
HospitalDoctorCreateRequestDto.java
HospitalDoctorDetailedResponseDto.java
HospitalDoctorResponseDto.java
HospitalPatientCreateRequestDto.java
HospitalPatientDetailedResponseDto.java
HospitalResponseDto.java
MedicationCreateRequestDto.java
MedicationResponseDto.java
MedicineCreateRequestDto.java
MedicineResponseDto.java
MedicineTakeCreateRequestDto.java
MedicineTakeDetailedResponseDto.java
PatientCaseCreateRequestDto.java
PatientCaseDetailedResponseDto.java
PatientCreateRequestDto.java
PatientResponseDto.java
ReminderCreateRequestDto.java
ReminderDetailedResponseDto.java
SpecializationCreateRequestDto.java
SpecializationResponseDto.java
```

### `src/main/java/com/carepulse/entity/` (19 files: models and enums)

```text
CaseStatus.java
Doctor.java
FollowUp.java
FollowUpStatus.java
Hospital.java
HospitalDoctor.java
HospitalPatient.java
Medication.java
Medicine.java
MedicineTake.java
MedicineTakeStatus.java
Patient.java
PatientCase.java
Reminder.java
ReminderType.java
RepeatType.java
Sex.java
Specialization.java
Status.java
```

### `src/main/java/com/carepulse/exception/` (13 files)

```text
DoctorNotFoundException.java
FollowUpException.java
GlobalExceptionHandler.java
HospitalDoctorException.java
HospitalNotFoundException.java
HospitalPatientException.java
MedicationException.java
MedicineException.java
MedicineTakeException.java
PatientCaseException.java
PatientNotFoundException.java
ReminderException.java
SpecializationException.java
```

### `src/main/java/com/carepulse/repository/` (12 files)

```text
DoctorRepository.java
FollowUpRepository.java
HospitalDoctorRepository.java
HospitalPatientRepository.java
HospitalRepository.java
MedicationRepository.java
MedicineRepository.java
MedicineTakeRepository.java
PatientCaseRepository.java
PatientRepository.java
ReminderRepository.java
SpecializationRepository.java
```

### `src/main/java/com/carepulse/service/` (12 files)

```text
DoctorService.java
FollowUpService.java
HospitalDoctorService.java
HospitalPatientService.java
HospitalService.java
MedicationService.java
MedicineService.java
MedicineTakeService.java
PatientCaseService.java
PatientService.java
ReminderService.java
SpecializationService.java
```

### `src/main/resources/` (2 tracked files)

```text
application.properties
logback-spring.xml
```

### `src/main/resources/static/` (4 files)

```text
api-explorer.html
api-lab.html
doctor-api-check.html
index.html
```

### `src/main/resources/static/css/` (3 files)

```text
api-explorer.css
api-lab.css
style.css
```

### `src/main/resources/static/js/` (4 files)

```text
api-explorer.js
api-lab.js
doctor-api-check.js
script.js
```

### `src/test/java/com/carepulse/controller/` (2 files)

```text
PatientServiceTest.java
TestControllerTest.java
```

## Current work in progress (not committed at this snapshot)

The working tree already contains edits to these **12 existing files**; the edits are not part of the completed/committed inventory above:

- `src/main/java/com/carepulse/controller/HospitalDoctorController.java` and `src/main/java/com/carepulse/service/HospitalDoctorService.java`: change the hospital-doctor create response to a detailed DTO.
- `src/main/java/com/carepulse/dto/DoctorCreateRequestDto.java`, `FollowUpCreateRequestDto.java`, `HospitalDoctorCreateRequestDto.java`, `MedicationCreateRequestDto.java`, `MedicineTakeCreateRequestDto.java`, `PatientCaseCreateRequestDto.java`, and `ReminderCreateRequestDto.java`: adjust validation messages and/or require positive related IDs.
- `src/main/java/com/carepulse/dto/HospitalCreateRequestDto.java` and `SpecializationCreateRequestDto.java`: remove persistence annotations from request DTOs.
- `src/main/java/com/carepulse/dto/HospitalPatientCreateRequestDto.java`: adjust ID validation; review before use because `@NotBlank` has been placed on `Long` fields (it is intended for character sequences).

These changes are recorded as **in progress**, not verified or committed by this document.

## Known gaps and next steps

1. Fix the reminder update route: `ReminderController.java` maps `{reminderid}` but binds `medicationId` in the method parameter. Verify the update endpoint after correcting it.
2. Review and test the uncommitted validation changes, especially `@NotBlank` on numeric IDs, then add controller/service tests beyond the two existing test files.
3. Add authentication, authorization, and appropriate protection of healthcare data before production exposure.
4. Add pagination/sorting to list endpoints as needed. A matching MySQL schema is currently required because JPA uses `ddl-auto=validate`.
5. Planned, **not completed**: outbound notifications (WhatsApp/SMS/email/push), family member support, full frontend/dashboards, audit logging, online consultations, and reporting/analytics. See `README.md` for the broader roadmap and API reference.

## Verification of this snapshot

- All 116 filenames in the tracked-file inventory were compared with `git ls-files`; none are missing or extra.
- Tests were **not run** for this snapshot: the `mvn` command is unavailable in the current environment. The test descriptions above are based on reading the test files, not on a new passing test run.