package com.carepulse.service;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.carepulse.dto.DoctorResponseDto;
import com.carepulse.dto.HospitalDoctorCreateRequestDto;
import com.carepulse.dto.HospitalDoctorDetailedResponseDto;
import com.carepulse.dto.HospitalDoctorResponseDto;
import com.carepulse.dto.HospitalResponseDto;
import com.carepulse.entity.Doctor;
import com.carepulse.entity.Hospital;
import com.carepulse.entity.HospitalDoctor;
import com.carepulse.entity.Status;
import com.carepulse.exception.HospitalDoctorException;
import com.carepulse.repository.HospitalDoctorRepository;

@Service
public class HospitalDoctorService {

	private final HospitalDoctorRepository hospitalDoctorRepository;
	private final DoctorService doctorService;
	private final HospitalService hospitalService;

	public HospitalDoctorService(HospitalDoctorRepository hospitalDoctorRepository, DoctorService doctorService,
			HospitalService hospitalService) {
		this.hospitalDoctorRepository = hospitalDoctorRepository;
		this.doctorService = doctorService;
		this.hospitalService = hospitalService;
	}

	private static final Logger logger = LoggerFactory.getLogger(HospitalDoctorService.class);

//	Get all hospital-doctors  (All Status)
	public List<HospitalDoctorDetailedResponseDto> getAllHospitalDoctors() {
		logger.info("Fetching all hospital doctors from the database");
		List<HospitalDoctor> hospitalDoctors = hospitalDoctorRepository.findAll();
		List<HospitalDoctorDetailedResponseDto> responseDtos = convertToDetailedResponseDtoList(hospitalDoctors);
		logger.info("Fetched {} hospital doctors from the database", hospitalDoctors.size());
		return responseDtos;
	}

//	Get all hospital-doctors  (Only Active Status)
	public List<HospitalDoctorDetailedResponseDto> getAllActiveHospitalDoctors() {
		logger.info("Fetching all hospital doctors from the database");
		List<HospitalDoctor> hospitalDoctors = hospitalDoctorRepository.findAllByStatus(Status.ACTIVE);
		List<HospitalDoctorDetailedResponseDto> responseDtos = convertToDetailedResponseDtoList(hospitalDoctors);
		logger.info("Fetched {} hospital doctors from the database", hospitalDoctors.size());
		return responseDtos;
	}

//	Get the list of all hospital-doctors by hospitalId
	public List<HospitalDoctorDetailedResponseDto> getAllHospitalDoctorsByHospitalId(Long hospitalId) {
		logger.info("Fetching all hospital doctors for hospitalId: {} from the database", hospitalId);
		List<HospitalDoctor> hospitalDoctors = hospitalDoctorRepository.findAllByHospital_HospitalId(hospitalId);
		List<HospitalDoctorDetailedResponseDto> responseDtos = convertToDetailedResponseDtoList(hospitalDoctors);
		logger.info("Fetched {} hospital doctors for hospitalId: {} from the database", hospitalDoctors.size(),
				hospitalId);
		return responseDtos;
	}

//	Get the list of all Active hospital-doctors by hospitalId
	public List<HospitalDoctorDetailedResponseDto> getAllActiveHospitalDoctorsByHospitalId(Long hospitalId) {
		logger.info("Fetching all hospital doctors for hospitalId: {} from the database", hospitalId);
		List<HospitalDoctor> hospitalDoctors = hospitalDoctorRepository
				.findAllByHospital_HospitalIdAndStatus(hospitalId, Status.ACTIVE);
		List<HospitalDoctorDetailedResponseDto> responseDtos = convertToDetailedResponseDtoList(hospitalDoctors);
		logger.info("Fetched {} hospital doctors for hospitalId: {} from the database", hospitalDoctors.size(),
				hospitalId);
		return responseDtos;
	}

//	Get the list of all hospital-doctors by doctorId
	public List<HospitalDoctorDetailedResponseDto> getAllHospitalDoctorsByDoctorId(Long doctorId) {
		logger.info("Fetching all hospital doctors for doctorId: {} from the database", doctorId);
		List<HospitalDoctor> hospitalDoctors = hospitalDoctorRepository.findAllByDoctor_DoctorId(doctorId);
		List<HospitalDoctorDetailedResponseDto> responseDtos = convertToDetailedResponseDtoList(hospitalDoctors);
		logger.info("Fetched {} hospital doctors for doctorId: {} from the database", hospitalDoctors.size(), doctorId);
		return responseDtos;
	}

//	Get the list of all Active hospital-doctors by doctorId
	public List<HospitalDoctorDetailedResponseDto> getAllActiveHospitalDoctorsByDoctorId(Long doctorId) {
		logger.info("Fetching all hospital doctors for doctorId: {} from the database", doctorId);
		List<HospitalDoctor> hospitalDoctors = hospitalDoctorRepository.findAllByDoctor_DoctorIdAndStatus(doctorId,
				Status.ACTIVE);
		List<HospitalDoctorDetailedResponseDto> responseDtos = convertToDetailedResponseDtoList(hospitalDoctors);
		logger.info("Fetched {} hospital doctors for doctorId: {} from the database", hospitalDoctors.size(), doctorId);
		return responseDtos;
	}

//	Add a new hospital-doctors
	public HospitalDoctorResponseDto addHospitalDoctor(HospitalDoctorCreateRequestDto requestDto) {
		logger.info("Adding new hospital doctor to the database");
		Long doctorId = requestDto.getDoctorId();
		Long hospitalId = requestDto.getHospitalId();

		if (hospitalDoctorRepository.findByHospital_HospitalIdAndDoctor_DoctorId(hospitalId, doctorId).isPresent()) {
			throw new HospitalDoctorException(
					"HospitalDoctor already exists with doctorId: " + doctorId + " and hospitalId: " + hospitalId);
		}
		Doctor doctor = doctorService.getDoctorEntityById(doctorId);
		Hospital hospital = hospitalService.getHospitalEntityById(hospitalId);
		HospitalDoctor hospitalDoctor = convertToEntity(requestDto, doctor, hospital);
		// Set the status to ACTIVE when adding a new hospital doctor
		hospitalDoctor.setStatus(Status.ACTIVE);
		// Set the join date to the current date
		hospitalDoctor.setJoinDate(LocalDate.now());
		HospitalDoctor savedHospitalDoctor = hospitalDoctorRepository.save(hospitalDoctor);
		HospitalDoctorResponseDto responseDto = convertToResponseDto(savedHospitalDoctor);
		logger.info("Added new hospital doctor to the database with doctorId: {} and hospitalId: {}",
				savedHospitalDoctor.getDoctor().getDoctorId(), savedHospitalDoctor.getHospital().getHospitalId());
		return responseDto;
	}

//	Soft delete a hospital doctor by setting the status to INACTIVE
	public HospitalDoctorResponseDto softDeleteHospitalDoctor(Long hospitalDoctorId) {
		logger.info("Soft deleting hospital doctor with id: {} from the database", hospitalDoctorId);
		HospitalDoctor hospitalDoctor = hospitalDoctorRepository.findById(hospitalDoctorId).orElseThrow(
				() -> new HospitalDoctorException("HospitalDoctor not found with id: " + hospitalDoctorId));
		hospitalDoctor.setStatus(Status.INACTIVE);
		hospitalDoctorRepository.save(hospitalDoctor);
		logger.info("Soft deleted hospital doctor with id: {} from the database", hospitalDoctorId);
		return convertToResponseDto(hospitalDoctor);
	}

//	Soft delete a hospital-doctors by doctorId and hospitalId
	public HospitalDoctorResponseDto softDeleteHospitalDoctorByDoctorIdAndHospitalId(Long doctorId, Long hospitalId) {
		logger.info("Soft deleting hospital doctor with doctorId: {} and hospitalId: {} from the database", doctorId,
				hospitalId);
		HospitalDoctor hospitalDoctor = hospitalDoctorRepository
				.findByHospital_HospitalIdAndDoctor_DoctorId(hospitalId, doctorId)
				.orElseThrow(() -> new HospitalDoctorException(
						"HospitalDoctor not found with doctorId: " + doctorId + " and hospitalId: " + hospitalId));
		hospitalDoctor.setStatus(Status.INACTIVE);
		hospitalDoctorRepository.save(hospitalDoctor);
		logger.info("Soft deleted hospital doctor with doctorId: {} and hospitalId: {} from the database", doctorId,
				hospitalId);
		return convertToResponseDto(hospitalDoctor);

	}

//	Helper method to convert HospitalDoctor entity to HospitalDoctorResponseDto
	private HospitalDoctorResponseDto convertToResponseDto(HospitalDoctor hospitalDoctor) {
		HospitalDoctorResponseDto responseDto = new HospitalDoctorResponseDto();
		responseDto.setHospitalDoctorId(hospitalDoctor.getHospitalDoctorId());
		responseDto.setDoctorId(hospitalDoctor.getDoctor().getDoctorId());
		responseDto.setHospitalId(hospitalDoctor.getHospital().getHospitalId());
		return responseDto;
	}

//	Helper method to convert HospitalDoctorCreateRequestDto to HospitalDoctor entity
	private HospitalDoctor convertToEntity(HospitalDoctorCreateRequestDto requestDto, Doctor doctor,
			Hospital hospital) {
		HospitalDoctor hospitalDoctor = new HospitalDoctor();
		hospitalDoctor.setDoctor(doctor);
		hospitalDoctor.setHospital(hospital);
		return hospitalDoctor;
	}

//	Convert a list of HospitalDoctor entities to a list of HospitalDoctorResponseDto
	private List<HospitalDoctorResponseDto> convertToResponseDtoList(List<HospitalDoctor> hospitalDoctors) {
		List<HospitalDoctorResponseDto> responseDtos = new java.util.ArrayList<>();
		for (HospitalDoctor hospitalDoctor : hospitalDoctors) {
			responseDtos.add(convertToResponseDto(hospitalDoctor));
		}
		return responseDtos;
	}

//	method to convert HospitalDoctor to HospitalDoctorDetailedResponseDto
	private HospitalDoctorDetailedResponseDto convertToDetailedResponseDto(HospitalDoctor hospitalDoctor) {
		HospitalDoctorDetailedResponseDto detailedResponseDto = new HospitalDoctorDetailedResponseDto();
		detailedResponseDto.setHospitalDoctorId(hospitalDoctor.getHospitalDoctorId());
		detailedResponseDto.setDoctorId(hospitalDoctor.getDoctor().getDoctorId());
		detailedResponseDto.setHospitalId(hospitalDoctor.getHospital().getHospitalId());
		// Fetch doctor details from DoctorService
		DoctorResponseDto doctordto = doctorService.getDoctorById(hospitalDoctor.getDoctor().getDoctorId());
		detailedResponseDto.setDoctorName(doctordto.getFullName());
		detailedResponseDto.setDoctorSpecialization(doctordto.getSpecializationId());
		detailedResponseDto.setDoctorRegistrationNo(doctordto.getDoctorRegistrationNo());
		// Fetch hospital details from HospitalService
		HospitalResponseDto hospitaldto = hospitalService.getHospitalById(hospitalDoctor.getHospital().getHospitalId());
		detailedResponseDto.setHospitalName(hospitaldto.getHospitalName());
		detailedResponseDto.setHospitalAddress(hospitaldto.getHospitalAddress());
		detailedResponseDto.setHospitalCode(hospitaldto.getHospitalCode());
		return detailedResponseDto;
	}

//	Convert a list of HospitalDoctor entities to a list of HospitalDoctorDetailedResponseDto
	private List<HospitalDoctorDetailedResponseDto> convertToDetailedResponseDtoList(
			List<HospitalDoctor> hospitalDoctors) {
		List<HospitalDoctorDetailedResponseDto> detailedResponseDtos = new java.util.ArrayList<>();
		for (HospitalDoctor hospitalDoctor : hospitalDoctors) {
			detailedResponseDtos.add(convertToDetailedResponseDto(hospitalDoctor));
		}
		return detailedResponseDtos;
	}
}
