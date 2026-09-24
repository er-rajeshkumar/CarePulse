package com.carepulse.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.carepulse.dto.PatientCaseCreateRequestDto;
import com.carepulse.dto.PatientCaseDetailedResponseDto;
import com.carepulse.entity.CaseStatus;
import com.carepulse.entity.PatientCase;
import com.carepulse.exception.PatientNotFoundException;
import com.carepulse.repository.DoctorRepository;
import com.carepulse.repository.HospitalRepository;
import com.carepulse.repository.PatientCaseRepository;
import com.carepulse.repository.PatientRepository;

@Service
public class PatientCaseService {

	private final PatientCaseRepository patientCaseRepository;
	private final PatientRepository patientRepository;
	private final DoctorRepository doctorRepository;
	private final HospitalRepository hospitalRepository;

	public PatientCaseService(PatientCaseRepository patientCaseRepository, PatientRepository patientRepository, DoctorRepository doctorRepository, HospitalRepository hospitalRepository) {
		this.patientCaseRepository = patientCaseRepository;
		this.patientRepository = patientRepository;
		this.doctorRepository = doctorRepository;
		this.hospitalRepository = hospitalRepository;
	}

	Logger logger = LoggerFactory.getLogger(PatientCaseService.class);

	public String getPatientCaseMessage() {
		return "Patient Case Service is working";
	}

//	Method to get all patient cases
	public List<PatientCaseDetailedResponseDto> getAllPatientCases() {
		logger.info("Fetching all patient cases");
		List<PatientCase> patientCases = patientCaseRepository.findAll();
		List<PatientCaseDetailedResponseDto> dto = new ArrayList<>();

		for (PatientCase patientCase : patientCases) {
			PatientCaseDetailedResponseDto patientCaseDto = convertToDto(patientCase);
			dto.add(patientCaseDto);
		}
		return dto;
		}

//	Method to check if a patient case exists by ID
	public boolean checkPatientCaseExistsById(Long id) {
		logger.info("Checking if patient case exists with ID: {}", id);
		return patientCaseRepository.existsById(id);
	}
	
//	Method to get patient case by ID
	public PatientCaseDetailedResponseDto getPatientCaseById(Long id) {
		logger.info("Fetching patient case with ID: {}", id);
		PatientCase patientCase = patientCaseRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Patient case not found with ID: " + id));
		return convertToDto(patientCase);
	}
	
//	Method to get patient case entity by ID
	public PatientCase getPatientCaseEntityById(Long id) {
		logger.info("Fetching patient case entity with ID: {}", id);
		PatientCase patientCase = patientCaseRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Patient case not found with ID: " + id));
		return patientCase;
	}

//	Method to get patient cases by patient ID
	public List<PatientCaseDetailedResponseDto> getPatientCasesByPatientId(Long patientId) {
		logger.info("Fetching patient cases for patient ID: {}", patientId);
		List<PatientCase> patientCases = patientCaseRepository.findByPatient_PatientId(patientId);
		List<PatientCaseDetailedResponseDto> dto = new ArrayList<>();
		for (PatientCase patientCase : patientCases) {
			dto.add(convertToDto(patientCase));
		}
		return dto;
	}

//	Method to get patient cases by doctor ID
	public List<PatientCaseDetailedResponseDto> getPatientCasesByDoctorId(Long doctorId) {
		logger.info("Fetching patient cases for doctor ID: {}", doctorId);
		List<PatientCase> patientCases = patientCaseRepository.findByDoctor_DoctorId(doctorId);
		List<PatientCaseDetailedResponseDto> dto = new ArrayList<>();
		for (PatientCase patientCase : patientCases) {
			dto.add(convertToDto(patientCase));
		}
		return dto;
	}

//	Method to get patient cases by hospital ID
	public List<PatientCaseDetailedResponseDto> getPatientCasesByHospitalId(Long hospitalId) {
		logger.info("Fetching patient cases for hospital ID: {}", hospitalId);
		List<PatientCase> patientCases = patientCaseRepository.findByHospital_HospitalId(hospitalId);
		List<PatientCaseDetailedResponseDto> dto = new ArrayList<>();
		for (PatientCase patientCase : patientCases) {
			dto.add(convertToDto(patientCase));
		}
		return dto;
	}

//	Method to add a new patient case
	public PatientCaseDetailedResponseDto addPatientCase(PatientCaseCreateRequestDto patientCaseCreateDto) {
		logger.info("Adding new patient case");
		PatientCase patientCase = convertToEntity(patientCaseCreateDto);
		PatientCase savedPatientCase = patientCaseRepository.save(patientCase);
		return convertToDto(savedPatientCase);
	}

//	Method for soft delete by patient case ID
	public void softDeletePatientCase(Long id) {
		logger.info("Soft deleting patient case with ID: {}", id);
		PatientCase patientCase = patientCaseRepository.findById(id)
				.orElseThrow(() -> new PatientNotFoundException("Patient case not found with ID: " + id));
		patientCase.setCaseStatus(CaseStatus.DELETED);
		patientCaseRepository.save(patientCase);
	}

//	Helper method to convert PatientCaseCreateRequestDto to PatientCase entity
	private PatientCase convertToEntity(PatientCaseCreateRequestDto patientCaseCreateDto) {
		PatientCase patientCase = new PatientCase();
		patientCase.setHospital(hospitalRepository.findById(patientCaseCreateDto.getHospitalId())
				.orElseThrow(() -> new RuntimeException("Hospital not found with ID: " + patientCaseCreateDto.getHospitalId())));
		patientCase.setPatient(patientRepository.findById(patientCaseCreateDto.getPatientId())
				.orElseThrow(() -> new RuntimeException("Patient not found with ID: " + patientCaseCreateDto.getPatientId())));
		patientCase.setDoctor(doctorRepository.findById(patientCaseCreateDto.getDoctorId())
				.orElseThrow(() -> new RuntimeException("Doctor not found with ID: " + patientCaseCreateDto.getDoctorId())));

		patientCase.setCaseTitle(patientCaseCreateDto.getCaseTitle());
		patientCase.setDiagnosis(patientCaseCreateDto.getDiagnosis());

		patientCase.setAdmissionDate(LocalDate.parse(patientCaseCreateDto.getAdmissionDate()));
		if (patientCaseCreateDto.getDischargeDate() != null && !patientCaseCreateDto.getDischargeDate().isBlank()) {
		    patientCase.setDischargeDate(LocalDate.parse(patientCaseCreateDto.getDischargeDate()));
		} else {
		    patientCase.setDischargeDate(null);
		}

		patientCase.setNotes(patientCaseCreateDto.getNote());

		return patientCase;
	}


//	Helper method to convert PatientCase entity to PatientCaseDetailedResponseDto
	private PatientCaseDetailedResponseDto convertToDto(PatientCase patientCase) {
		PatientCaseDetailedResponseDto dto = new PatientCaseDetailedResponseDto();
		dto.setId(patientCase.getId());
		dto.setHospitalId(patientCase.getHospital().getHospitalId());
		dto.setHospitalName(patientCase.getHospital().getHospitalName());
		dto.setHospitalAddress(patientCase.getHospital().getHospitalAddress());

		dto.setPatientId(patientCase.getPatient().getPatientId());
		StringBuilder fullName = new StringBuilder();
		if (patientCase.getPatient().getFirstName() != null) {
		    fullName.append(patientCase.getPatient().getFirstName());
		}
		if (patientCase.getPatient().getMiddleName() != null
		        && !patientCase.getPatient().getMiddleName().isBlank()) {
		    fullName.append(" ")
		            .append(patientCase.getPatient().getMiddleName());
		}
		if (patientCase.getPatient().getLastName() != null
		        && !patientCase.getPatient().getLastName().isBlank()) {
		    fullName.append(" ")
		            .append(patientCase.getPatient().getLastName());
		}
		dto.setPatientFullName(fullName.toString().trim());
		dto.setPatientEmail(patientCase.getPatient().getEmail());
		dto.setPatientPhone(patientCase.getPatient().getPhone());

		dto.setDoctorId(patientCase.getDoctor().getDoctorId());
		dto.setDoctorFullName(patientCase.getDoctor().getFirstName() + " " + patientCase.getDoctor().getLastName());
		dto.setDoctorEmail(patientCase.getDoctor().getEmail());
		dto.setCaseTitle(patientCase.getCaseTitle());
		dto.setDiagnosis(patientCase.getDiagnosis());
		dto.setAdmissionDate(patientCase.getAdmissionDate().toString());
		dto.setCaseStatus(patientCase.getCaseStatus());
		dto.setNotes(patientCase.getNotes());
		dto.setDischargeDate(patientCase.getDischargeDate() != null ? patientCase.getDischargeDate().toString() : null);
		return dto;
	}
}
