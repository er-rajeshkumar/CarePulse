package com.carepulse.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.carepulse.dto.PatientCreateRequestDto;
import com.carepulse.dto.PatientResponseDto;
import com.carepulse.entity.Patient;
import com.carepulse.entity.Sex;
import com.carepulse.entity.Status;
import com.carepulse.exception.PatientNotFoundException;
import com.carepulse.repository.PatientRepository;
@Service
public class PatientService {

	private final PatientRepository patientRepository;

    public String getPatientMessage() {
        return "Patient Service is working";
    }
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    private static final Logger logger =
            LoggerFactory.getLogger(PatientService.class);

    public void testLog() {

        logger.trace("TRACE Log");
        logger.debug("DEBUG Log");
        logger.info("INFO Log");
        logger.warn("WARN Log");
        logger.error("ERROR Log");
    }

    public Patient getPatientEntityById(Long id) {
		logger.info("Fetching patient entity with id: {} from the database", id);
		Patient patient = patientRepository.findById(id).orElseThrow(() ->
			new PatientNotFoundException("Patient not found with id: " + id)
		);
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
    	logger.debug("Total Patient founds: {}" , patientDtos.size());
        return patientDtos;
    }


    public List<PatientResponseDto>  getAllPatientsByStatus() {
    	List<Patient> patients =  patientRepository.findAllByStatus(Status.ACTIVE);
    	List<PatientResponseDto> patientDtos = new java.util.ArrayList<>();
    	for (Patient patient : patients) {
		    PatientResponseDto dto = convertToDto(patient);
		    patientDtos.add(dto);
    	}
    	logger.info("Fetched all active patients from the database");
    	logger.debug("Total Active Patient founds: {}" , patientDtos.size());
		return patientDtos;
    }
    public PatientResponseDto getPatientById(Long id) {
    	Patient patient =  patientRepository.findByPatientIdAndStatus(id, Status.ACTIVE).orElseThrow(() ->
        new PatientNotFoundException(
                "Patient not found with id: " + id
            )
        );
    	if(patient.getStatus() == Status.DELETED) {
			throw new PatientNotFoundException("Patient with id: " + id + " is deleted");
		}
    	PatientResponseDto dto = convertToDto(patient);
    	logger.info("Fetched patient with id: " + id);
    	logger.debug("Patient details: " + dto.toString());
    	return dto;
    }

	public PatientResponseDto addPatient(PatientCreateRequestDto request) {

		if(patientRepository.existsByEmail(request.getEmail())) {
			throw new PatientNotFoundException("Patient with email: " + request.getEmail() + " already exists");
		}
		if(patientRepository.existsByPhone(request.getPhone())) {
			throw new PatientNotFoundException("Patient with phone: " + request.getPhone() + " already exists");
		}
		Patient patient = new Patient();
		patient.setFirstName(request.getFirstName());
		patient.setMiddleName(request.getMiddleName());
		patient.setLastName(request.getLastName());
		patient.setPhone(request.getPhone());
		patient.setEmail(request.getEmail());
		patient.setSex(
			    Sex.valueOf(request.getSex().toUpperCase())
			);
		patient.setAddress(request.getAddress());
		logger.info("Adding new patient: " + patient.getFirstName() + " " + patient.getLastName());
		logger.debug("Patient details: " + patient.toString());
		Patient saved =  patientRepository.save(patient);
		PatientResponseDto dto =  convertToDto(saved);
		logger.info("Added new patient with id: " + saved.getPatientId());
		return dto;
	}

	public PatientResponseDto updatePatient(Long id, PatientCreateRequestDto request) {
		Patient existingPatient = patientRepository.findById(id)
				.orElseThrow(() ->
				new PatientNotFoundException("Patient not found with id: " + id));
		if (request.getFirstName() != null) {
			existingPatient.setFirstName(request.getFirstName());
			existingPatient.setMiddleName(request.getMiddleName());
			existingPatient.setLastName(request.getLastName());
			existingPatient.setPhone(request.getPhone());
			existingPatient.setEmail(request.getEmail());
			existingPatient.setSex(Sex.valueOf(request.getSex().toUpperCase()));
			existingPatient.setAddress(request.getAddress());
			existingPatient.setStatus(request.getStatus());
			existingPatient = patientRepository.save(existingPatient);
		}
		logger.info("Updated patient with id: " + id);
		logger.debug("Updated patient details: " + existingPatient.toString());
		return convertToDto(existingPatient);
	}

	public Patient deletePatient(Long id) {
		Patient existingPatient = patientRepository.findById(id)
				.orElseThrow(() ->
				new PatientNotFoundException("Patient not found with id: " + id));
//		Status status = Status.DELETED;
		existingPatient.setStatus(Status.DELETED);
		patientRepository.save(existingPatient);

		logger.info("Deleted patient with id: " + id);
		return existingPatient;
	}
	
//	Helper method to convert Patient entity to PatientResponseDto
	private PatientResponseDto convertToDto(Patient patient) {
		PatientResponseDto dto = new PatientResponseDto();
		dto.setPatientId(patient.getPatientId());
		
		String lastName = patient.getLastName() != null ? patient.getLastName() : "";
		String middleName = patient.getMiddleName() != null ? patient.getMiddleName() : "";
		String fullName = patient.getFirstName() + " " + middleName + " " + lastName;
		dto.setFullName(fullName.trim());
		dto.setPhone(patient.getPhone());
		dto.setEmail(patient.getEmail());
		dto.setSex(patient.getSex());
		dto.setAddress(patient.getAddress());
		dto.setStatus(patient.getStatus());
		dto.setDob(patient.getDob() != null ? patient.getDob().toString() : null);
		return dto;
	}
}