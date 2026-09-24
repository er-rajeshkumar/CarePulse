package com.carepulse.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.carepulse.dto.FollowUpCreateRequestDto;
import com.carepulse.dto.FollowUpDetailedResponseDto;
import com.carepulse.entity.Doctor;
import com.carepulse.entity.FollowUp;
import com.carepulse.entity.Hospital;
import com.carepulse.entity.Patient;
import com.carepulse.entity.PatientCase;
import com.carepulse.repository.FollowUpRepository;

@Service
public class FollowUpService {

	private static final Logger logger = LoggerFactory.getLogger(FollowUpService.class);

	private final FollowUpRepository followUpRepository;
	private final PatientCaseService patientCaseService;

	public FollowUpService(FollowUpRepository followUpRepository,
			PatientCaseService patientCaseService) {
		this.followUpRepository = followUpRepository;
		this.patientCaseService = patientCaseService;
	}

	// Method to test the service
	public String getMessage() {
		return "FollowUpService is working!";
	}

	// Method to get all follow-ups
	public List<FollowUpDetailedResponseDto> getAllFollowUps() {
		List<FollowUp> followUps = followUpRepository.findAll();
		List<FollowUpDetailedResponseDto> followUpDtos = new ArrayList<>();
		for (FollowUp followUp : followUps) {
			followUpDtos.add(convertToDto(followUp));
		}
		return followUpDtos;
	}

	// Method to get follow-up by ID
	public FollowUpDetailedResponseDto getFollowUpById(Long followUpId) {
		FollowUp followUp = followUpRepository.findById(followUpId)
				.orElseThrow(() -> new RuntimeException("Follow-up not found with ID: " + followUpId));
		return convertToDto(followUp);
	}

	// Method to get follow-ups by PatientCase ID
	public List<FollowUpDetailedResponseDto> getFollowUpsByPatientCaseId(Long patientCaseId) {
		List<FollowUp> followUps = followUpRepository.findByPatientCaseId(patientCaseId);
		List<FollowUpDetailedResponseDto> followUpDtos = new ArrayList<>();
		for (FollowUp followUp : followUps) {
			followUpDtos.add(convertToDto(followUp));
		}
		return followUpDtos;
	}

	// Method to get follow-ups by Doctor ID
	public List<FollowUpDetailedResponseDto> getFollowUpsByDoctorId(Long doctorId) {
		List<FollowUp> followUps = followUpRepository.findByPatientCase_Doctor_DoctorId(doctorId);
		List<FollowUpDetailedResponseDto> followUpDtos = new ArrayList<>();
		for (FollowUp followUp : followUps) {
			followUpDtos.add(convertToDto(followUp));
		}
		return followUpDtos;
	}

	// Method to get follow-ups by Patient ID
	public List<FollowUpDetailedResponseDto> getFollowUpsByPatientId(Long patientId) {
		List<FollowUp> followUps = followUpRepository.findByPatientCase_Patient_PatientId(patientId);
		List<FollowUpDetailedResponseDto> followUpDtos = new ArrayList<>();
		for (FollowUp followUp : followUps) {
			followUpDtos.add(convertToDto(followUp));
		}
		return followUpDtos;
	}

	// Method to get follow-ups by Hospital ID
	public List<FollowUpDetailedResponseDto> getFollowUpsByHospitalId(Long hospitalId) {
		List<FollowUp> followUps = followUpRepository.findByPatientCase_Hospital_HospitalId(hospitalId);
		List<FollowUpDetailedResponseDto> followUpDtos = new ArrayList<>();
		for (FollowUp followUp : followUps) {
			followUpDtos.add(convertToDto(followUp));
		}
		return followUpDtos;
	}

	// Method to create new follow-up
	public FollowUpDetailedResponseDto createFollowUp(FollowUpCreateRequestDto dto) {
		logger.info("Creating new follow-up for patient case id: {}", dto.getPatientCaseId());
		FollowUp followUp = convertToEntity(dto);
		followUp.setCreatedAt(LocalDateTime.now());
		FollowUp savedFollowUp = followUpRepository.save(followUp);
		return convertToDto(savedFollowUp);
	}

	// Method to update existing follow-up
	public FollowUpDetailedResponseDto updateFollowUp(Long followUpId, FollowUpCreateRequestDto dto) {
		logger.info("Updating follow-up with id: {}", followUpId);
		FollowUp existingFollowUp = followUpRepository.findById(followUpId)
				.orElseThrow(() -> {
					logger.error("Follow-up not found with ID: {}", followUpId);
					return new RuntimeException("Follow-up not found with ID: " + followUpId);
				});

		// Update fields
		existingFollowUp.setPatientCase(patientCaseService.getPatientCaseEntityById(dto.getPatientCaseId()));
		existingFollowUp.setFollowUpDate(dto.getFollowUpDate());
		existingFollowUp.setFollowUpTime(dto.getFollowUpTime());
		existingFollowUp.setPurpose(dto.getPurpose());
		existingFollowUp.setFollowUpStatus(dto.getFollowUpStatus());
		existingFollowUp.setNotes(dto.getNotes());
		existingFollowUp.setUpdatedAt(LocalDateTime.now());

		FollowUp updatedFollowUp = followUpRepository.save(existingFollowUp);
		return convertToDto(updatedFollowUp);
	}

	// Helper method to convert FollowUp entity to FollowUpDetailedResponseDto
	public FollowUpDetailedResponseDto convertToDto(FollowUp followUp) {
		FollowUpDetailedResponseDto dto = new FollowUpDetailedResponseDto();
		dto.setFollowUpId(followUp.getFollowUpId());

		// Set PatientCase ID
		dto.setPatientCaseId(followUp.getPatientCase().getId());

		// Set Hospital details
		Hospital hospital = followUp.getPatientCase().getHospital();
		dto.setHospitalId(hospital.getHospitalId());
		dto.setHospitalName(hospital.getHospitalName());
		dto.setHospitalAddress(hospital.getHospitalAddress());

		// Set Patient details
		Patient patient = followUp.getPatientCase().getPatient();
		dto.setPatientId(patient.getPatientId());
		dto.setPatientFullName(patient.getFirstName() + " " + patient.getLastName());
		dto.setPatientEmail(patient.getEmail());
		dto.setPatientPhone(patient.getPhone());

		// Set Doctor details
		Doctor doctor = followUp.getPatientCase().getDoctor();
		dto.setDoctorId(doctor.getDoctorId());
		dto.setDoctorFullName(doctor.getFirstName() + " " + doctor.getLastName());
		dto.setDoctorEmail(doctor.getEmail());

		// Set FollowUp details
		dto.setFollowUpDate(followUp.getFollowUpDate());
		dto.setFollowUpTime(followUp.getFollowUpTime());
		dto.setPurpose(followUp.getPurpose());
		dto.setFollowUpStatus(followUp.getFollowUpStatus());
		dto.setNotes(followUp.getNotes());

		return dto;
	}

	// Helper method to convert HospitalCreateRequestDto to FollowUp entity
	public FollowUp convertToEntity(FollowUpCreateRequestDto dto) {
		FollowUp followUp = new FollowUp();
		PatientCase patientCase = patientCaseService.getPatientCaseEntityById(dto.getPatientCaseId());
		followUp.setPatientCase(patientCase);
		followUp.setFollowUpDate(dto.getFollowUpDate());
		followUp.setFollowUpTime(dto.getFollowUpTime());
		followUp.setPurpose(dto.getPurpose());
		followUp.setFollowUpStatus(dto.getFollowUpStatus());
		followUp.setNotes(dto.getNotes());
		return followUp;
	}

}
