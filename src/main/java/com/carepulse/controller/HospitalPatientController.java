package com.carepulse.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.carepulse.dto.HospitalPatientCreateRequestDto;
import com.carepulse.dto.HospitalPatientDetailedResponseDto;
import com.carepulse.service.HospitalPatientService;

import jakarta.validation.Valid;

@RestController
public class HospitalPatientController {

	private final HospitalPatientService hospitalPatientService;

	public HospitalPatientController(HospitalPatientService hospitalPatientService) {
		this.hospitalPatientService = hospitalPatientService;
	}

	@GetMapping("/carepulse/hospital-patient/test")
	public String getHospitalPatientMessage() {
		return "Hospital Patient Controller is working";
	}

	@GetMapping("/carepulse/hospital-patient")
	public List<HospitalPatientDetailedResponseDto> getAllHospitalPatient() {
		return hospitalPatientService.getAllHospitalPatient();
	}

	@GetMapping("/carepulse/hospital-patient/hospital/{hospitalId}")
	public List<HospitalPatientDetailedResponseDto> getAllHospitalPatientByHospitalId(@PathVariable Long hospitalId) {
		return hospitalPatientService.getAllHospitalPatientByHospitalId(hospitalId);
	}

	@GetMapping("/carepulse/hospital-patient/patient/{patientId}")
	public List<HospitalPatientDetailedResponseDto> getAllHospitalPatientByPatientId(@PathVariable Long patientId) {
		return hospitalPatientService.getAllHospitalPatientByPatientId(patientId);
	}

	@PostMapping("/carepulse/hospital-patient")
	public HospitalPatientDetailedResponseDto addHospitalPatient(
			@RequestBody @Valid HospitalPatientCreateRequestDto hospitalPatientDto) {
		return hospitalPatientService.addHospitalPatient(hospitalPatientDto);
	}
}
