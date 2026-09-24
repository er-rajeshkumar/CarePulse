package com.carepulse.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.carepulse.dto.HospitalCreateRequestDto;
import com.carepulse.dto.HospitalResponseDto;
import com.carepulse.entity.Hospital;
import com.carepulse.entity.Status;
import com.carepulse.exception.HospitalNotFoundException;
import com.carepulse.repository.HospitalRepository;

@Service
public class HospitalService {

	private final HospitalRepository hospitalRepository;

	public HospitalService(HospitalRepository hospitalRepository) {
		this.hospitalRepository = hospitalRepository;
	}

	private static final Logger logger = LoggerFactory.getLogger(HospitalService.class);

	public String getHospitalMessage() {
		return "Hospital Service is working";
	}

	public boolean isHospitalExistsByEmail(String email) {
		logger.info("Checking if hospital exists with email: {}", email);
		boolean exists = hospitalRepository.existsByHospitalEmail(email);
		logger.info("Hospital exists with email {}: {}", email, exists);
		return exists;
	}

	public boolean isHospitalExistsByCode(String code) {
		logger.info("Checking if hospital exists with code: {}", code);
		boolean exists = hospitalRepository.existsByHospitalCode(code);
		logger.info("Hospital exists with code {}: {}", code, exists);
		return exists;
	}

	public boolean isHospitalExistsById(Long id) {
		logger.info("Checking if hospital exists with id: {}", id);
		boolean exists = hospitalRepository.existsByHospitalId(id);
		logger.info("Hospital exists with id {}: {}", id, exists);
		return exists;
	}

	public Hospital getHospitalEntityById(Long id) {
		logger.info("Fetching hospital entity with id: {} from the database", id);
		Hospital hospital = hospitalRepository.findById(id)
				.orElseThrow(() -> new HospitalNotFoundException("Hospital not found with id: " + id));
		logger.info("Fetched hospital entity with id: {} from the database", id);
		return hospital;
	}

	public List<HospitalResponseDto> getAllHospitals() {
		logger.info("Fetching all hospitals from the database");
		List<Hospital> hospitals = hospitalRepository.findAll();
		List<HospitalResponseDto> hospitalResponseDtos = new java.util.ArrayList<>();
		for (Hospital hospital : hospitals) {
			HospitalResponseDto hospitalResponseDto = mapHospitaltoDto(hospital);
			hospitalResponseDtos.add(hospitalResponseDto);
		}
		logger.info("Fetched {} hospitals from the database", hospitals.size());
		return hospitalResponseDtos;
	}

	public HospitalResponseDto getHospitalById(Long id) {
		logger.info("Fetching hospital with id: {} from the database", id);
		Hospital hospital = hospitalRepository.findById(id).orElseThrow(() ->

		new HospitalNotFoundException("Hospital not found with id: " + id));
		HospitalResponseDto hospitalResponseDto = mapHospitaltoDto(hospital);
		logger.info("Fetched hospital with id: {} from the database", id);
		logger.debug("Hospital details: {}", hospital);
		return hospitalResponseDto;
	}

	public List<HospitalResponseDto> getAllHospitalsByStatus() {
		logger.info("Fetching all hospitals with status ACTIVE from the database");
		List<Hospital> hospitals = hospitalRepository.findAllByStatus(Status.ACTIVE);
		List<HospitalResponseDto> hospitalResponseDtos = new java.util.ArrayList<>();
		for (Hospital hospital : hospitals) {
			HospitalResponseDto hospitalResponseDto = mapHospitaltoDto(hospital);
			hospitalResponseDtos.add(hospitalResponseDto);
		}
		logger.info("Fetched {} hospitals with status ACTIVE from the database", hospitals.size());
		return hospitalResponseDtos;
	}

	public HospitalResponseDto addHospital(HospitalCreateRequestDto hospitalCreateRequestDto) {
		logger.info("Adding new hospital to the database");
		if (hospitalRepository.existsByHospitalEmail(hospitalCreateRequestDto.getHospitalEmail())) {
			logger.error("Hospital with email: {} already exists in the database",
					hospitalCreateRequestDto.getHospitalEmail());
			throw new IllegalArgumentException(
					"Hospital with email: " + hospitalCreateRequestDto.getHospitalEmail() + " already exists");
		}
		if (hospitalRepository.existsByHospitalCode(hospitalCreateRequestDto.getHospitalCode())) {
			logger.error("Hospital with code: {} already exists in the database",
					hospitalCreateRequestDto.getHospitalCode());
			throw new IllegalArgumentException(
					"Hospital with code: " + hospitalCreateRequestDto.getHospitalCode() + " already exists");
		}
		Hospital hospital = mapCreateDtoToHospital(hospitalCreateRequestDto);
		hospital.setStatus(Status.ACTIVE);
		hospital.setCreatedAt(java.time.LocalDateTime.now());
		Hospital savedHospital = hospitalRepository.save(hospital);
		logger.info("Added new hospital with id: {} to the database", savedHospital.getHospitalId());
		HospitalResponseDto hospitalResponseDto = mapHospitaltoDto(savedHospital);
		return hospitalResponseDto;
	}

	public HospitalResponseDto updateHospital(Long id, HospitalCreateRequestDto hospitalCreateRequestDto) {
		logger.info("Updating hospital with id: {} in the database", id);
		Hospital existingHospital = hospitalRepository.findById(id)
				.orElseThrow(() -> new HospitalNotFoundException("Hospital not found with id: " + id));
		if (hospitalRepository.existsByHospitalCodeAndHospitalIdNot(hospitalCreateRequestDto.getHospitalCode(), id)) {

			throw new HospitalNotFoundException(
					"Hospital code already exists: " + hospitalCreateRequestDto.getHospitalCode());
		}
		existingHospital.setHospitalName(hospitalCreateRequestDto.getHospitalName());
		existingHospital.setHospitalCode(hospitalCreateRequestDto.getHospitalCode());
		existingHospital.setHospitalEmail(hospitalCreateRequestDto.getHospitalEmail());
		existingHospital.setHospitalPhone(hospitalCreateRequestDto.getHospitalPhone());
		existingHospital.setHospitalAddress(hospitalCreateRequestDto.getHospitalAddress());
		existingHospital.setUpdatedAt(java.time.LocalDateTime.now());
		Hospital updatedHospital = hospitalRepository.save(existingHospital);
		logger.info("Updated hospital with id: {} in the database", updatedHospital.getHospitalId());
		HospitalResponseDto hospitalResponseDto = mapHospitaltoDto(updatedHospital);
		return hospitalResponseDto;
	}

	public HospitalResponseDto deleteHospital(Long id) {
		logger.info("Deleting hospital with id: {} from the database", id);
		Hospital existingHospital = hospitalRepository.findById(id)
				.orElseThrow(() -> new HospitalNotFoundException("Hospital not found with id: " + id));
		existingHospital.setStatus(Status.DELETED);
		existingHospital.setUpdatedAt(java.time.LocalDateTime.now());
		hospitalRepository.save(existingHospital);
		logger.info("Deleted hospital with id: {} from the database", id);
		HospitalResponseDto hospitalResponseDto = mapHospitaltoDto(existingHospital);
		return hospitalResponseDto;
	}

	public Hospital mapCreateDtoToHospital(HospitalCreateRequestDto hospitalCreateRequestDto) {
		Hospital hospital = new Hospital();
		hospital.setHospitalName(hospitalCreateRequestDto.getHospitalName());
		hospital.setHospitalCode(hospitalCreateRequestDto.getHospitalCode());
		hospital.setHospitalEmail(hospitalCreateRequestDto.getHospitalEmail());
		hospital.setHospitalPhone(hospitalCreateRequestDto.getHospitalPhone());
		hospital.setHospitalAddress(hospitalCreateRequestDto.getHospitalAddress());
		hospital.setStatus(Status.ACTIVE);
		return hospital;
	}

	public HospitalResponseDto mapHospitaltoDto(Hospital hospital) {
		HospitalResponseDto hospitalResponseDto = new HospitalResponseDto();
		hospitalResponseDto.setHospitalId(hospital.getHospitalId());
		hospitalResponseDto.setHospitalName(hospital.getHospitalName());
		hospitalResponseDto.setHospitalCode(hospital.getHospitalCode());
		hospitalResponseDto.setHospitalEmail(hospital.getHospitalEmail());
		hospitalResponseDto.setHospitalPhone(hospital.getHospitalPhone());
		hospitalResponseDto.setHospitalAddress(hospital.getHospitalAddress());
		return hospitalResponseDto;
	}

}
