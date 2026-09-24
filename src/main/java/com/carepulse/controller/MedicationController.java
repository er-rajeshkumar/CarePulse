package com.carepulse.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.carepulse.dto.MedicationCreateRequestDto;
import com.carepulse.dto.MedicationResponseDto;
import com.carepulse.service.MedicationService;

import jakarta.validation.Valid;

@RestController
public class MedicationController {

	private final MedicationService medicationService;

	public MedicationController(MedicationService medicationService) {
		this.medicationService = medicationService;
	}

	@GetMapping("/carepulse/medication/message")
	public String getMessage() {
		return medicationService.getMessage();
	}

	@GetMapping("/carepulse/medication")
	public List<MedicationResponseDto> getAllMedications() {
		return medicationService.getAllMedications();
	}

	@GetMapping("/carepulse/medication/{medicationId}")
	public MedicationResponseDto getMedicationById(@PathVariable Long medicationId) {
		return medicationService.getMedicationById(medicationId);
	}

	@GetMapping("/carepulse/medication/patientCase/{patientCaseId}")
	public List<MedicationResponseDto> getAllMedicationsByPatientCaseId(@PathVariable Long patientCaseId) {
		return medicationService.getAllMedicationsByPatientCaseId(patientCaseId);
	}

	@GetMapping("/carepulse/medication/doctor/{doctorId}")
	public List<MedicationResponseDto> getAllMedicationsByDoctorId(@PathVariable Long doctorId) {
		return medicationService.getAllMedicationsByDoctorId(doctorId);
	}

	@PostMapping("/carepulse/medication")
	public MedicationResponseDto createMedication(
			@Valid @RequestBody MedicationCreateRequestDto medicationCreateRequestDto) {
		return medicationService.createMedication(medicationCreateRequestDto);
	}

	@PutMapping("/carepulse/medication/{medicationId}")
	public MedicationResponseDto updateMedication(@PathVariable Long medicationId,
			@Valid @RequestBody MedicationCreateRequestDto medicationCreateRequestDto) {
		return medicationService.updateMedication(medicationId, medicationCreateRequestDto);
	}
}
