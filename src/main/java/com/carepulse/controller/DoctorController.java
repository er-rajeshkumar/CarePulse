package com.carepulse.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
//import org.hibernate.mapping.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.carepulse.dto.DoctorCreateRequestDto;
import com.carepulse.dto.DoctorResponseDto;
import com.carepulse.service.DoctorService;

import jakarta.validation.Valid;

@RestController
public class DoctorController {

	private final DoctorService doctorService;

	public DoctorController(DoctorService doctorService) {
		this.doctorService = doctorService;
	}

//	Test doctor service
	@GetMapping("/carepulse/doctors/test")
	public String testDoctorService() {
		return doctorService.getDoctorMessage();
	}

//	Get all doctors active and inactive
	@GetMapping("/carepulse/doctors")
	public List<DoctorResponseDto> getAllDoctors() {
		return doctorService.getAllDoctor();
	}

//	Get all active doctors
	@GetMapping("/carepulse/doctors/active")
	public List<DoctorResponseDto> getAllActiveDoctors() {
		return doctorService.getAllDoctorByStatus();
	}

//	Get doctor by id
	@GetMapping("/carepulse/doctors/{id}")
	public ResponseEntity<?> getDoctorById(@PathVariable Long id) {
		DoctorResponseDto doctorResponseDto = doctorService.getDoctorById(id);
		return new ResponseEntity<>(doctorResponseDto, HttpStatus.OK);
	}

//	Update doctor by id
	@PutMapping("/carepulse/doctors/{id}")
	public ResponseEntity<?> updateDoctorById(@PathVariable Long id,
			@Valid @RequestBody DoctorCreateRequestDto doctorCreateRequestDto) {
		DoctorResponseDto updatedDoctor = doctorService.updateDoctorById(id, doctorCreateRequestDto);
		return new ResponseEntity<>(updatedDoctor, HttpStatus.OK);
	}

//	Add new doctor
	@PostMapping("/carepulse/doctors")
	public ResponseEntity<?> addDoctor(@Valid @RequestBody DoctorCreateRequestDto doctorCreateRequestDto) {
		DoctorResponseDto newDoctor = doctorService.addDoctor(doctorCreateRequestDto);
		return new ResponseEntity<>(newDoctor, HttpStatus.CREATED);
	}

//	Delete doctor by id
	@DeleteMapping("/carepulse/doctors/{id}")
	public ResponseEntity<?> deleteDoctorById(@PathVariable Long id) {
		doctorService.deleteDoctorById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}