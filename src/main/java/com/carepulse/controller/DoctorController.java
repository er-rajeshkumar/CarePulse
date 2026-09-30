package com.carepulse.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.carepulse.dto.DoctorCreateRequestDto;
import com.carepulse.dto.DoctorResponseDto;
import com.carepulse.dto.updateDto.DoctorUpdateRequestDto;
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
	public Map<String, Object> testDoctorService() {
		Map<String, Object> response = new java.util.HashMap<>();
		response.put("message", "Doctor service is working fine");
		response.put("status", "success");
		response.put("data", doctorService.getDoctorMessage());
		return response;
	}

//	Get all doctors active and inactive
	@GetMapping("/carepulse/doctors")
	public Map<String, Object> getAllDoctors() {
		Map<String, Object> response = new java.util.HashMap<>();
		response.put("message", "All doctors retrieved successfully");
		response.put("status", "success");
		response.put("data", doctorService.getAllDoctor());
		return response;
	}

//	Get all active doctors
	@GetMapping("/carepulse/doctors/active")
	public Map<String, Object> getAllActiveDoctors() {
		Map<String, Object> response = new java.util.HashMap<>();
		response.put("message", "All active doctors retrieved successfully");
		response.put("status", "success");
		response.put("data", doctorService.getAllDoctorByStatus());
		return response;
	}

//	Get doctor by id
	@GetMapping("/carepulse/doctors/{id}")
	public Map<String, Object> getDoctorById(@PathVariable Long id) {
		Map<String, Object> response = new java.util.HashMap<>();
		response.put("message", "Doctor retrieved successfully");
		response.put("status", "success");
		response.put("data", doctorService.getDoctorById(id));
		return response;
	}

//	Update doctor by id
	@PutMapping("/carepulse/doctors/{id}")
	public Map<String, Object> updateDoctorById(@PathVariable Long id,
			@Valid @RequestBody DoctorUpdateRequestDto doctorUpdateRequestDto) {
		Map<String, Object> response = new java.util.HashMap<>();
		response.put("message", "Doctor updated successfully");
		response.put("status", "success");
		response.put("data", doctorService.updateDoctorById(id, doctorUpdateRequestDto));
		return response;
	}

//	Add new doctor
	@PostMapping("/carepulse/doctors")
	public Map<String, Object> addDoctor(@Valid @RequestBody DoctorCreateRequestDto doctorCreateRequestDto) {
		DoctorResponseDto newDoctor = doctorService.addDoctor(doctorCreateRequestDto);
		Map<String, Object> response = new java.util.HashMap<>();
		response.put("message", "Doctor added successfully");
		response.put("status", "success");
		response.put("data", newDoctor);
		
		return response;
	}

//	Delete doctor by id
	@DeleteMapping("/carepulse/doctors/{id}")
	public Map<String, Object> deleteDoctorById(@PathVariable Long id) {
		Map<String, Object> response = new java.util.HashMap<>();
		response.put("message", "Doctor deleted successfully");
		response.put("status", "success");
		response.put("data", doctorService.deleteDoctorById(id));
		return response;
	}

}