package com.carepulse.service;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.carepulse.dto.DoctorCreateRequestDto;
import com.carepulse.dto.DoctorResponseDto;
import com.carepulse.entity.Doctor;
import com.carepulse.entity.Status;
import com.carepulse.exception.DoctorNotFoundException;
import com.carepulse.repository.DoctorRepository;
@Service
public class DoctorService {

	private final DoctorRepository doctorRepository;

	public String getDoctorMessage() {
		return "Doctor Service is working";
	}
	public DoctorService(DoctorRepository doctorRepository) {
		this.doctorRepository = doctorRepository;
	}

	private static final Logger logger =LoggerFactory.getLogger(DoctorService.class);

	public void testLog() {
		logger.trace("TRACE Log");
		logger.debug("DEBUG Log");
		logger.info("INFO Log");
		logger.warn("WARN Log");
		logger.error("ERROR Log");
	}

//	Method to get all doctors from the database
	public List<DoctorResponseDto> getAllDoctor() {
		logger.info("Fetching all Doctor from the database");
		List<Doctor> doctors =  doctorRepository.findAll();
		List<DoctorResponseDto> doctorResponseDtos = new java.util.ArrayList<>();
		for (Doctor doctor : doctors) {
			DoctorResponseDto doctorResponseDto = mapToDto(doctor);
			doctorResponseDtos.add(doctorResponseDto);
		}
		return doctorResponseDtos;
	}

//	Method to get all active doctors from the database
	public List<DoctorResponseDto> getAllDoctorByStatus() {
		logger.info("Fetching all active Doctor from the database");
		List<Doctor> doctors =  doctorRepository.findAllByStatus(Status.ACTIVE);
		List<DoctorResponseDto> doctorResponseDtos = new java.util.ArrayList<>();
		for (Doctor doctor : doctors) {
			DoctorResponseDto doctorResponseDto = mapToDto(doctor);
			doctorResponseDtos.add(doctorResponseDto);
		}
		return doctorResponseDtos;
	}

//	Method to get doctor by id from the database
	public DoctorResponseDto getDoctorById(Long id) {
		logger.info("Fetching Doctor with id " + id +" from the database");
		Doctor doctor =  doctorRepository.findByDoctorIdAndStatus(id, Status.ACTIVE).orElseThrow(() ->
		new DoctorNotFoundException(
				"Doctor not found with id: " + id
			)
		);
		DoctorResponseDto doctorResponseDto = mapToDto(doctor);
		return doctorResponseDto;
	}

//	Method to update doctor by id from the database
	public DoctorResponseDto updateDoctorById(Long id, DoctorCreateRequestDto doctorCreateRequestDto) {
		logger.info("Updating Doctor with id " + id +" in the database");
		Doctor doctor =  doctorRepository.findByDoctorIdAndStatus(id, Status.ACTIVE).orElseThrow(() ->
		new DoctorNotFoundException(
				"Doctor not found with id: " + id
			)
		);
		doctor.setFirstName(doctorCreateRequestDto.getFirstName());
		doctor.setLastName(doctorCreateRequestDto.getLastName());
		doctor.setSex(doctorCreateRequestDto.getSex());
		doctor.setSpecializationId(doctorCreateRequestDto.getSpecializationId());
		doctor.setEmail(doctorCreateRequestDto.getEmail());
		doctor.setPhone(doctorCreateRequestDto.getPhone());
		doctor.setDoctorRegistrationNo(doctorCreateRequestDto.getDoctorRegistrationNo());
		doctor.setStatus(Status.ACTIVE);
		doctor.setUpdatedAt(java.time.LocalDateTime.now());
		doctorRepository.save(doctor);
		return mapToDto(doctor);
	}

//	Method to add new doctor to the database
	public DoctorResponseDto addDoctor(DoctorCreateRequestDto doctorCreateRequestDto) {
		logger.info("Adding new Doctor to the database");
		logger.debug("DoctorCreateRequestDto: " + doctorCreateRequestDto.toString());
		if(doctorRepository.existsByEmail(doctorCreateRequestDto.getEmail())){
			logger.error("Doctor email already exists: " + doctorCreateRequestDto.getEmail());
		    throw new RuntimeException("Doctor email already exists");
		}
		if(doctorRepository.existsByDoctorRegistrationNo(doctorCreateRequestDto.getDoctorRegistrationNo())){
		    logger.error("Registration number already exists: " + doctorCreateRequestDto.getDoctorRegistrationNo());
			throw new RuntimeException("Registration number already exists");
		}
		Doctor doctor = new Doctor();
		doctor.setFirstName(doctorCreateRequestDto.getFirstName());
		doctor.setLastName(doctorCreateRequestDto.getLastName());
		doctor.setSex(doctorCreateRequestDto.getSex());
		doctor.setSpecializationId(doctorCreateRequestDto.getSpecializationId());
		doctor.setStatus(Status.ACTIVE);
		doctor.setEmail(doctorCreateRequestDto.getEmail());
		doctor.setPhone(doctorCreateRequestDto.getPhone());
		doctor.setDoctorRegistrationNo(doctorCreateRequestDto.getDoctorRegistrationNo());
		doctor.setCreatedAt(java.time.LocalDateTime.now());
		
		Doctor savedDoctor = doctorRepository.save(doctor);
		logger.info("Doctor created successfully with id {}",savedDoctor.getDoctorId());
		return mapToDto(savedDoctor);
	}

//	Delete doctor by id from the database
	public void deleteDoctorById(Long id) {
		logger.info("Deleting Doctor with id " + id +" from the database");
		Doctor doctor =  doctorRepository.findByDoctorIdAndStatus(id, Status.ACTIVE).orElseThrow(() ->
		new DoctorNotFoundException(
				"Doctor not found with id: " + id
			)
		);
		doctor.setStatus(Status.DELETED);
		doctorRepository.save(doctor);
	}



//	Method to map Doctor entity to DoctorResponseDto
//	Helper method to map Doctor entity to DoctorResponseDto
	private DoctorResponseDto mapToDto(Doctor doctor) {
	    DoctorResponseDto dto = new DoctorResponseDto();
	    dto.setFullName(doctor.getFirstName() + " " + doctor.getLastName());
	    dto.setSex(doctor.getSex());
	    dto.setSpecializationId(doctor.getSpecializationId());
	    dto.setEmail(doctor.getEmail());
	    dto.setPhone(doctor.getPhone());
	    dto.setDoctorRegistrationNo(doctor.getDoctorRegistrationNo());
	    return dto;	}

}

