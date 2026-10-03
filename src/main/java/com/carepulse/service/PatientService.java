package com.carepulse.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.carepulse.dto.createDto.PatientCreateRequestDto;
import com.carepulse.dto.responceDto.PatientResponseDto;
import com.carepulse.dto.updateDto.PatientUpdateRequestDto;
import com.carepulse.entity.Patient;
import com.carepulse.enums.Status;
import com.carepulse.exception.DuplicateEntityException;
import com.carepulse.exception.EntityNotFoundException;
import com.carepulse.exception.PatientNotFoundException;
import com.carepulse.repository.PatientRepository;

import jakarta.transaction.Transactional;

@Service
public class PatientService {

	private final PatientRepository patientRepository;

	public String getPatientMessage() {
		return "Patient Service is working";
	}

	public PatientService(PatientRepository patientRepository) {
		this.patientRepository = patientRepository;
	}

	private static final Logger logger = LoggerFactory.getLogger(PatientService.class);

	public void testLog() {

		logger.trace("TRACE Log");
		logger.debug("DEBUG Log");
		logger.info("INFO Log");
		logger.warn("WARN Log");
		logger.error("ERROR Log");
	}

	public Patient getPatientEntityById(Long id) {
		logger.info("Fetching patient entity with id: {} from the database", id);
		Patient patient = patientRepository.findById(id)
				.orElseThrow(() -> new PatientNotFoundException("Patient not found with id: " + id));
		logger.info("Fetched patient entity with id: {} from the database", id);
		return patient;
	}

	public List<PatientResponseDto> getAllPatients() {
		logger.info("Fetching all patients from the database");
		List<Patient> patients = patientRepository.findAll();
		List<PatientResponseDto> patientDtos = new java.util.ArrayList<>();
		for (Patient patient : patients) {
			PatientResponseDto dto = convertToDto(patient);
			patientDtos.add(dto);
		}
		logger.info("Fetched all patients from the database");
		logger.debug("Total Patient founds: {}", patientDtos.size());
		return patientDtos;
	}

	public List<PatientResponseDto> getAllPatientsByStatus() {
		List<Patient> patients = patientRepository.findAllByStatus(Status.ACTIVE);
		List<PatientResponseDto> patientDtos = new java.util.ArrayList<>();
		for (Patient patient : patients) {
			PatientResponseDto dto = convertToDto(patient);
			patientDtos.add(dto);
		}
		logger.info("Fetched all active patients from the database");
		logger.debug("Total Active Patient founds: {}", patientDtos.size());
		return patientDtos;
	}

	public PatientResponseDto getPatientById(Long id) {
		Patient patient = patientRepository.findByPatientIdAndStatus(id, Status.ACTIVE)
				.orElseThrow(() -> new EntityNotFoundException("Patient not found with id: " + id));
		if (patient.getStatus() == Status.DELETED) {
			throw new PatientNotFoundException("Patient with id: " + id + " is deleted");
		}
		PatientResponseDto dto = convertToDto(patient);
		logger.info("Fetched patient with id: " + id);
		logger.debug("Patient details: " + dto.toString());
		return dto;
	}

	// Method to add a new patient to the database
	@Transactional
	public PatientResponseDto addPatient(PatientCreateRequestDto request) {

		if (patientRepository.existsByEmail(request.getEmail())) {
			throw new DuplicateEntityException("Patient with email: " + request.getEmail() + " already exists");
		}
		if (patientRepository.existsByPhone(request.getPhone())) {
			throw new DuplicateEntityException("Patient with phone: " + request.getPhone() + " already exists");
		}
		Patient patient = convertCreateDtoToEntity(request);
		logger.info("Adding new patient: " + patient.getFirstName() + " " + patient.getLastName());
		logger.debug("Patient details: " + patient.toString());
		Patient saved = patientRepository.save(patient);
		PatientResponseDto dto = convertToDto(saved);
		logger.info("Added new patient with id: " + saved.getPatientId());
		return dto;
	}

	// Method to update an existing patient in the database
	@Transactional
	public PatientResponseDto updatePatient(Long id, PatientUpdateRequestDto request) {
		Patient existingPatient = patientRepository.findByPatientIdAndStatus(id, Status.ACTIVE)
				.orElseThrow(() -> new EntityNotFoundException("Patient not found with id: " + id + " or already deleted"));
		modifyPatientEntity(existingPatient, request);
		patientRepository.save(existingPatient);
		return convertToDto(existingPatient);
	}

	// Method to delete a patient from the database
	@Transactional
	public Patient deletePatient(Long id) {
		boolean exists = patientRepository.existsById(id);
		if (!exists) {
			throw new EntityNotFoundException("Patient not found with id: " + id);
		}
//		Status status = Status.DELETED;
		Patient existingPatient = patientRepository.findByPatientIdAndStatus(id, Status.ACTIVE)
				.orElseThrow(() -> new EntityNotFoundException("Patient not found with id: " + id + " or already deleted"));
		existingPatient.setStatus(Status.DELETED);
		patientRepository.save(existingPatient);

		logger.info("Deleted patient with id: " + id);
		return existingPatient;
	}

//	Helper method to convert Patient entity to PatientResponseDto
	private PatientResponseDto convertToDto(Patient patient) {
	    PatientResponseDto dto = new PatientResponseDto();
	    dto.setPatientId(patient.getPatientId());

	    String firstName = patient.getFirstName() != null ? patient.getFirstName().trim() : "";
	    String middleName = patient.getMiddleName() != null ? patient.getMiddleName().trim() : "";
	    String lastName = patient.getLastName() != null ? patient.getLastName().trim() : "";

	    String fullName = String.join(" ",
	            java.util.stream.Stream.of(firstName, middleName, lastName)
	                    .filter(name -> !name.isEmpty())
	                    .toList());

	    dto.setFullName(fullName);
	    dto.setPhone(patient.getPhone());
	    dto.setEmail(patient.getEmail());
	    dto.setSex(patient.getSex());
	    dto.setAddress(patient.getAddress());
	    dto.setStatus(patient.getStatus());
	    dto.setDob(patient.getDob() != null ? patient.getDob().toString() : null);

	    return dto;
	}

//	Helper method to convert PatientCreateRequestDto to Patient entity
	public Patient convertCreateDtoToEntity(PatientCreateRequestDto request) {
		Patient patient = new Patient();
		patient.setFirstName(request.getFirstName());
		patient.setMiddleName(request.getMiddleName());
		patient.setLastName(request.getLastName());
		patient.setPhone(request.getPhone());
		patient.setEmail(request.getEmail());
		patient.setSex(request.getSex());
		patient.setStatus(Status.ACTIVE);
		patient.setDob(request.getDob());
		patient.setAddress(request.getAddress());
		return patient;
	}


//	Helper method to convert PatientUpdateRequestDto to Patient entity
	public Patient convertUpdateDtoToEntity(PatientUpdateRequestDto request, Long id) {
		Patient patient = new Patient();
		patient.setPatientId(id);
		if (request.getFirstName() != null) {
			patient.setFirstName(request.getFirstName());
		}
		if (request.getMiddleName() != null) {
			patient.setMiddleName(request.getMiddleName());
		}
		if (request.getLastName() != null) {
			patient.setLastName(request.getLastName());
		}
		if (request.getPhone() != null) {
			patient.setPhone(request.getPhone());
		}
		if (request.getEmail() != null) {
			patient.setEmail(request.getEmail());
		}
		if (request.getSex() != null) {
			patient.setSex(request.getSex());
		}
		if (request.getAddress() != null) {
			patient.setAddress(request.getAddress());
		}
		if (request.getStatus() != null) {
			patient.setStatus(request.getStatus());
		}
		if (request.getDob() != null) {
			patient.setDob(request.getDob());
		}


		return patient;
	}

//	Helper method to modify Patient entity with PatientUpdateRequestDto
	public Patient modifyPatientEntity(Patient existingPatient, PatientUpdateRequestDto request) {
		if (request.getFirstName() != null) {
			existingPatient.setFirstName(request.getFirstName());
		}
		if (request.getMiddleName() != null) {
			existingPatient.setMiddleName(request.getMiddleName());
		}
		if (request.getLastName() != null) {
			existingPatient.setLastName(request.getLastName());
		}
		if (request.getPhone() != null) {
			existingPatient.setPhone(request.getPhone());
		}
		if (request.getEmail() != null) {
			existingPatient.setEmail(request.getEmail());
		}
		if (request.getSex() != null) {
			existingPatient.setSex(request.getSex());
		}
		if (request.getAddress() != null) {
			existingPatient.setAddress(request.getAddress());
		}
		if (request.getStatus() != null) {
			existingPatient.setStatus(request.getStatus());
		}
		if (request.getDob() != null) {
			existingPatient.setDob(request.getDob());
		}

		return existingPatient;
	}
}