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

    public List<Patient> getAllPatients() {
    	logger.info("Fetching all patients from the database");
        return patientRepository.findAll();
    }


    public List<Patient> getAllPatientsByStatus() {
    	return patientRepository.findAllByStatus(Status.ACTIVE);
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
    	PatientResponseDto dto = new PatientResponseDto();
    	dto.setPatientId(patient.getPatientId());
    	dto.setFullName(
    	        patient.getFirstName()
    	        + " "
    	        + patient.getLastName());

    	dto.setPhone(patient.getPhone());
    	logger.info("Fetched patient with id: " + id);
    	logger.debug("Patient details: " + dto.toString());
    	return dto;
    }

	public Patient addPatient(PatientCreateRequestDto request) {

		Patient patient = new Patient();
		patient.setFirstName(request.getFirstName());
		patient.setMiddleName(request.getMiddleName());
		patient.setLastName(request.getLastName());
		patient.setPhone(request.getPhone());
		patient.setEmail(request.getEmail());
		patient.setSex(
			    Sex.valueOf(request.getSex().toUpperCase())
			);
		logger.info("Adding new patient: " + patient.getFirstName() + " " + patient.getLastName());
		logger.debug("Patient details: " + patient.toString());
		return patientRepository.save(patient);
	}

	public Patient updatePatient(Long id, PatientCreateRequestDto request) {
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
			existingPatient = patientRepository.save(existingPatient);
		}
		logger.info("Updated patient with id: " + id);
		logger.debug("Updated patient details: " + existingPatient.toString());
		return existingPatient;
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
}