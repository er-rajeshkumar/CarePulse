package com.carepulse.service;

import org.springframework.stereotype.Service;
import com.carepulse.repository.HospitalPatientRepository;
import com.carepulse.entity.Hospital;
import com.carepulse.entity.HospitalPatient;
import com.carepulse.entity.Patient;
import java.util.List;
import com.carepulse.dto.HospitalPatientDetailedResponseDto;
import com.carepulse.dto.HospitalPatientCreateRequestDto;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class HospitalPatientService {

	public String getHospitalPatientMessage() {
		return "Hospital Patient Service is working";
	}
	
	private final HospitalPatientRepository hospitalPatientRepository;
	
	public HospitalPatientService(HospitalPatientRepository hospitalPatientRepository) {
		this.hospitalPatientRepository = hospitalPatientRepository;
	}
	
	Logger logger = LoggerFactory.getLogger(HospitalPatientService.class);
	
//	Method to get all data of hospital and patient from db
	public List<HospitalPatientDetailedResponseDto> getAllHospitalPatient() {
		logger.info("Fetching all Hospital and Patient data from the database");
		List<HospitalPatient> hospitalPatientDetailedResponseDtos = hospitalPatientRepository.findAll();
		List<HospitalPatientDetailedResponseDto> hospitalPatientDetailedResponseDtoList = new java.util.ArrayList<>();
		for (HospitalPatient hospitalPatient : hospitalPatientDetailedResponseDtos) {
			HospitalPatientDetailedResponseDto dto = mapToDto(hospitalPatient);
			hospitalPatientDetailedResponseDtoList.add(dto);
		}
		return hospitalPatientDetailedResponseDtoList;
	}
	
//	method to get all data of hospital and patient from db by hospitalId
	public List<HospitalPatientDetailedResponseDto> getAllHospitalPatientByHospitalId(Long hospitalId) {
		logger.info("Fetching all Hospital and Patient data from the database for hospitalId: {}", hospitalId);
		List<HospitalPatient> hospitalPatientDetailedResponseDtos = hospitalPatientRepository.findAllByHospital_HospitalId(hospitalId);
		List<HospitalPatientDetailedResponseDto> hospitalPatientDetailedResponseDtoList = new java.util.ArrayList<>();
		for (HospitalPatient hospitalPatient : hospitalPatientDetailedResponseDtos) {
			HospitalPatientDetailedResponseDto dto = mapToDto(hospitalPatient);
			hospitalPatientDetailedResponseDtoList.add(dto);
		}
		return hospitalPatientDetailedResponseDtoList;
	}
	
//	method to get all data of hospital and patient from db by patientId
	public List<HospitalPatientDetailedResponseDto> getAllHospitalPatientByPatientId(Long patientId) {
		logger.info("Fetching all Hospital and Patient data from the database for patientId: {}", patientId);
		List<HospitalPatient> hospitalPatientDetailedResponseDtos = hospitalPatientRepository.findAllByPatient_PatientId(patientId);
		List<HospitalPatientDetailedResponseDto> hospitalPatientDetailedResponseDtoList = new java.util.ArrayList<>();
		for (HospitalPatient hospitalPatient : hospitalPatientDetailedResponseDtos) {
			HospitalPatientDetailedResponseDto dto = mapToDto(hospitalPatient);
			hospitalPatientDetailedResponseDtoList.add(dto);
		}
		return hospitalPatientDetailedResponseDtoList;
	}
	
//	Method to add a new HospitalPatient entry to the database
	public HospitalPatientDetailedResponseDto addHospitalPatient(HospitalPatientCreateRequestDto requestDto) {
		logger.info("Adding new HospitalPatient entry to the database");
		Hospital hospital = new Hospital();
		hospital.setHospitalId(requestDto.getHospitalId());
		
		Patient patient = new Patient();
		patient.setPatientId(requestDto.getPatientId());
		
		HospitalPatient hospitalPatient = new HospitalPatient();
		hospitalPatient.setHospital(hospital);
		hospitalPatient.setPatient(patient);
		
		HospitalPatient savedHospitalPatient = hospitalPatientRepository.save(hospitalPatient);
		
		return mapToDto(savedHospitalPatient);
	}
	
//	Helper method to map HospitalPatient entity to HospitalPatientDetailedResponseDto
	private HospitalPatientDetailedResponseDto mapToDto(HospitalPatient hospitalPatient) {
		HospitalPatientDetailedResponseDto dto = new HospitalPatientDetailedResponseDto();
		dto.setHospitalId(hospitalPatient.getHospital().getHospitalId());
		dto.setHospitalName(hospitalPatient.getHospital().getHospitalName());
		dto.setPatientId(hospitalPatient.getPatient().getPatientId());
		
		dto.setPatientFullName(hospitalPatient.getPatient().getFirstName() + " " + hospitalPatient.getPatient().getLastName());
		dto.setPatientGender(hospitalPatient.getPatient().getSex().toString());
		dto.setPatientDateOfBirth(hospitalPatient.getPatient().getDob().toString());
		dto.setPatientEmail(hospitalPatient.getPatient().getEmail());
		dto.setPatientPhoneNumber(hospitalPatient.getPatient().getPhone());
		dto.setPatientAddress(hospitalPatient.getPatient().getAddress());
		return dto;
	}
	
}
