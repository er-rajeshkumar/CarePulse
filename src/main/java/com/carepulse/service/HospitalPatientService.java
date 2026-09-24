package com.carepulse.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.carepulse.dto.HospitalPatientCreateRequestDto;
import com.carepulse.dto.HospitalPatientDetailedResponseDto;
import com.carepulse.entity.Hospital;
import com.carepulse.entity.HospitalPatient;
import com.carepulse.entity.Patient;
import com.carepulse.repository.HospitalPatientRepository;

@Service
public class HospitalPatientService {

	public String getHospitalPatientMessage() {
		return "Hospital Patient Service is working";
	}

	private final HospitalPatientRepository hospitalPatientRepository;
	private final HospitalService hospitalService;
	private final PatientService patientService;

	public HospitalPatientService(HospitalPatientRepository hospitalPatientRepository, HospitalService hospitalService,
			PatientService patientService) {
		this.hospitalPatientRepository = hospitalPatientRepository;
		this.hospitalService = hospitalService;
		this.patientService = patientService;
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
		List<HospitalPatient> hospitalPatientDetailedResponseDtos = hospitalPatientRepository
				.findAllByHospital_HospitalId(hospitalId);
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
		List<HospitalPatient> hospitalPatientDetailedResponseDtos = hospitalPatientRepository
				.findAllByPatient_PatientId(patientId);
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

		Long hospitalId = requestDto.getHospitalId();
		Long patientId = requestDto.getPatientId();

		// Check whether patient is already registered in this hospital
		if (hospitalPatientRepository.findByHospital_HospitalIdAndPatient_PatientId(hospitalId, patientId)
				.isPresent()) {

			throw new RuntimeException("Patient is already registered in hospital. " + "hospitalId: " + hospitalId
					+ ", patientId: " + patientId);
		}

		// Get existing entities
		Hospital hospital = hospitalService.getHospitalEntityById(hospitalId);
		Patient patient = patientService.getPatientEntityById(patientId);

		// Create relationship
		HospitalPatient hospitalPatient = new HospitalPatient();

		hospitalPatient.setHospital(hospital);
		hospitalPatient.setPatient(patient);

		HospitalPatient savedHospitalPatient = hospitalPatientRepository.save(hospitalPatient);

		return mapToDto(savedHospitalPatient);
	}

//	Helper method to map HospitalPatient entity to HospitalPatientDetailedResponseDto
	private HospitalPatientDetailedResponseDto mapToDto(HospitalPatient hospitalPatient) {
		HospitalPatientDetailedResponseDto dto = new HospitalPatientDetailedResponseDto();
		dto.setHospitalPatientId(hospitalPatient.getHospitalPatientId());
		dto.setHospitalPatientNumber(hospitalPatient.getHospitalPatientNo());
		dto.setHospitalId(hospitalPatient.getHospital().getHospitalId());
		dto.setHospitalName(hospitalPatient.getHospital().getHospitalName());
		dto.setPatientId(hospitalPatient.getPatient().getPatientId());

		dto.setPatientFullName(
				hospitalPatient.getPatient().getFirstName() + " " + hospitalPatient.getPatient().getLastName());
		dto.setPatientGender(hospitalPatient.getPatient().getSex().toString());
		if (hospitalPatient.getPatient().getDob() != null) {
			dto.setPatientDateOfBirth(hospitalPatient.getPatient().getDob().toString());
		}
		dto.setPatientEmail(hospitalPatient.getPatient().getEmail());
		dto.setPatientPhoneNumber(hospitalPatient.getPatient().getPhone());
		dto.setPatientAddress(hospitalPatient.getPatient().getAddress());
		return dto;
	}

}
