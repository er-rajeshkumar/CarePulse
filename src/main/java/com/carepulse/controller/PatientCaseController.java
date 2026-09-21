package com.carepulse.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.carepulse.dto.PatientCaseCreateRequestDto;
import com.carepulse.dto.PatientCaseDetailedResponseDto;
import com.carepulse.service.PatientCaseService;

import jakarta.validation.Valid;

@RestController
public class PatientCaseController {

	private final PatientCaseService patientCaseService;
	
	public PatientCaseController(PatientCaseService patientCaseService) {
		this.patientCaseService = patientCaseService;
	}
	
	@GetMapping("/carepulse/patient-case/test")
	public String testPatientCaseService() {
		return patientCaseService.getPatientCaseMessage();
	}
	
	@GetMapping("/carepulse/patient-case")
	public List<PatientCaseDetailedResponseDto> getAllPatientCases() {
		return patientCaseService.getAllPatientCases();
	}
	
	@GetMapping("/carepulse/patient-case/{id}")
	public PatientCaseDetailedResponseDto getPatientCaseById(@PathVariable Long id) {
		return patientCaseService.getPatientCaseById(id);
	}
	
	@GetMapping("/carepulse/patient-case/patient/{patientId}")
	public List<PatientCaseDetailedResponseDto> getPatientCasesByPatientId(@PathVariable Long patientId) {
		return patientCaseService.getPatientCasesByPatientId(patientId);
	}
	
	@GetMapping("/carepulse/patient-case/doctor/{doctorId}")
	public List<PatientCaseDetailedResponseDto> getPatientCasesByDoctorId(@PathVariable Long doctorId) {
		return patientCaseService.getPatientCasesByDoctorId(doctorId);
	}
	
	@GetMapping("/carepulse/patient-case/hospital/{hospitalId}")
	public List<PatientCaseDetailedResponseDto> getPatientCasesByHospitalId(@PathVariable Long hospitalId) {
		return patientCaseService.getPatientCasesByHospitalId(hospitalId);
	}
	
	@PostMapping("/carepulse/patient-case")
	public PatientCaseDetailedResponseDto createPatientCase(@RequestBody @Valid PatientCaseCreateRequestDto patientCaseDto) {
		return patientCaseService.addPatientCase(patientCaseDto);
	}
}
