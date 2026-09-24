package com.carepulse.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.carepulse.dto.SpecializationCreateRequestDto;
import com.carepulse.dto.SpecializationResponseDto;
import com.carepulse.entity.Specialization;
import com.carepulse.entity.Status;
import com.carepulse.exception.SpecializationException;
import com.carepulse.repository.SpecializationRepository;

@Service
public class SpecializationService {

	private final SpecializationRepository specializationRepository;

	public SpecializationService(SpecializationRepository specializationRepository) {
		this.specializationRepository = specializationRepository;
	}

	private static final Logger logger = LoggerFactory.getLogger(SpecializationService.class);

	public String getSpecializationMessage() {
		return "Specialization Service is working";
	}

	public void testLog() {
		logger.trace("TRACE Log");
		logger.debug("DEBUG Log");
		logger.info("INFO Log");
		logger.warn("WARN Log");
		logger.error("ERROR Log");
	}

	public List<SpecializationResponseDto> getAllSpecializations() {
		logger.info("Fetching all Specializations from the database");
		List<Specialization> specializations = specializationRepository.findAll();
		List<SpecializationResponseDto> specializationResponseDtos = new java.util.ArrayList<>();
		for (Specialization specialization : specializations) {
			logger.info("Specialization ID: {}, Name: {}", specialization.getSpecializationId(),
					specialization.getSpecializationName());
			specializationResponseDtos.add(mapToDto(specialization));
		}
		return specializationResponseDtos;
	}

	public List<SpecializationResponseDto> getAllActiveSpecializations() {
		logger.info("Fetching all active Specializations from the database");
		List<Specialization> specializations = specializationRepository
				.findAllByStatus(com.carepulse.entity.Status.ACTIVE);
		List<SpecializationResponseDto> specializationResponseDtos = new java.util.ArrayList<>();
		for (Specialization specialization : specializations) {
			logger.info("Specialization ID: {}, Name: {}", specialization.getSpecializationId(),
					specialization.getSpecializationName());
			specializationResponseDtos.add(mapToDto(specialization));
		}
		return specializationResponseDtos;
	}

	public SpecializationResponseDto getSpecializationById(Long id) {
		logger.info("Fetching Specialization with ID: {}", id);
		Specialization specialization = specializationRepository.findBySpecializationIdAndStatus(id, Status.ACTIVE)
				.orElseThrow(() -> new SpecializationException("Specialization not found with ID: " + id));
		logger.info("Found Specialization: ID: {}, Name: {}", specialization.getSpecializationId(),
				specialization.getSpecializationName());
		SpecializationResponseDto specializationResponseDto = mapToDto(specialization);
		return specializationResponseDto;
	}

	public SpecializationResponseDto addSpecialization(SpecializationCreateRequestDto specializationCeateRequestDto) {
		Specialization specialization = mapToEntity(specializationCeateRequestDto);
		if (specializationRepository.existsBySpecializationCode(specialization.getSpecializationCode())) {
			throw new SpecializationException("Specialization code already exists");
		}
		Specialization savedSpecialization = specializationRepository.save(specialization);
		logger.info("Added Specialization: ID: {}, Name: {}", savedSpecialization.getSpecializationId(),
				savedSpecialization.getSpecializationName());
		return mapToDto(savedSpecialization);
	}

	public SpecializationResponseDto updateSpecialization(Long id,
			SpecializationCreateRequestDto specializationCreateRequestDto) {
		Specialization specialization = specializationRepository.findBySpecializationIdAndStatus(id, Status.ACTIVE)
				.orElseThrow(() -> new SpecializationException("Specialization not found with ID: " + id));
		if (specializationRepository.findBySpecializationCodeAndSpecializationIdNot(
				specializationCreateRequestDto.getSpecializationCode(), id).isPresent()) {
			throw new SpecializationException("Specialization code already exists");
		}
		specialization.setSpecializationCode(specializationCreateRequestDto.getSpecializationCode());
		specialization.setSpecializationName(specializationCreateRequestDto.getSpecializationName());
		specialization.setSpecializationDescription(specializationCreateRequestDto.getSpecializationDescription());
		Specialization updatedSpecialization = specializationRepository.save(specialization);
		logger.info("Updated Specialization: ID: {}, Name: {}", updatedSpecialization.getSpecializationId(),
				updatedSpecialization.getSpecializationName());
		return mapToDto(updatedSpecialization);
	}

	public SpecializationResponseDto deleteSpecialization(Long id) {
		Specialization specialization = specializationRepository.findBySpecializationIdAndStatus(id, Status.ACTIVE)
				.orElseThrow(() -> new SpecializationException("Specialization not found with ID: " + id));
		specialization.setStatus(Status.DELETED);
		Specialization deletedSpecialization = specializationRepository.save(specialization);
		logger.info("Deleted Specialization: ID: {}, Name: {}", deletedSpecialization.getSpecializationId(),
				deletedSpecialization.getSpecializationName());
		return mapToDto(deletedSpecialization);
	}

	private SpecializationResponseDto mapToDto(Specialization specialization) {
		SpecializationResponseDto specializationResponseDto = new SpecializationResponseDto();
		specializationResponseDto.setSpecializationId(specialization.getSpecializationId());
		specializationResponseDto.setSpecializationCode(specialization.getSpecializationCode());
		specializationResponseDto.setSpecializationName(specialization.getSpecializationName());
		specializationResponseDto.setSpecializationDescription(specialization.getSpecializationDescription());
		return specializationResponseDto;
	}

	private Specialization mapToEntity(SpecializationCreateRequestDto specializationCreateRequestDto) {
		Specialization specialization = new Specialization();
		specialization.setSpecializationCode(specializationCreateRequestDto.getSpecializationCode());
		specialization.setSpecializationName(specializationCreateRequestDto.getSpecializationName());
		specialization.setSpecializationDescription(specializationCreateRequestDto.getSpecializationDescription());
		specialization.setStatus(Status.ACTIVE);
		return specialization;
	}
}
