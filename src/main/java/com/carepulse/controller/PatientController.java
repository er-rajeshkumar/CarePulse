package com.carepulse.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import org.hibernate.mapping.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.carepulse.dto.PatientCreateRequestDto;
import com.carepulse.dto.PatientResponseDto;
import com.carepulse.entity.Patient;
import com.carepulse.service.PatientService;

import jakarta.validation.Valid;

@RestController
public class PatientController {

	private final PatientService patientService;

	public PatientController(PatientService patientService) {
		this.patientService = patientService;
	}

//    dummy code
	@GetMapping("/carepulse/patients/test")
	public String testPatientService() {
		return patientService.getPatientMessage();
	}

	@GetMapping("/carepulse/patients")
	public List<PatientResponseDto> getAllPatients() {
		return patientService.getAllPatients();
	}

	@GetMapping("/carepulse/patients/active")
	public List<PatientResponseDto> getAllActivePatients() {
		return patientService.getAllPatientsByStatus();
	}
//    @GetMapping("/api/patients/{id}")
//    public Patient getPatientById(@PathVariable Long id) {
//		return patientService.getPatientById(id);
//	}

	@GetMapping("/carepulse/patients/{id}")
	public ResponseEntity<?> getPatientById(@PathVariable Long id) {

		PatientResponseDto patientdto = patientService.getPatientById(id);

		return ResponseEntity.ok(patientdto);
	}

	@PostMapping("/carepulse/patients")
	public PatientResponseDto addPatient(@Valid @RequestBody PatientCreateRequestDto request) {

		PatientResponseDto patientDto = patientService.addPatient(request);

		// Create a response map with the success message and patient ID
		Map<String, String> response = new HashMap<>();
		response.put("message", "Patient added successfully");
		response.put("patientId", patientDto.getPatientId().toString());

		return patientDto;
	}

	@PutMapping("/carepulse/patients/{id}")
	public PatientResponseDto updatePatient(@PathVariable Long id,
			@Valid @RequestBody PatientCreateRequestDto request) {

		PatientResponseDto updatedPatient = patientService.updatePatient(id, request);

		// Create a response map with the success message and patient ID
		Map<String, String> response = new HashMap<>();
		response.put("message", "Patient updated successfully");
		response.put("patientId", updatedPatient.getPatientId().toString());

		return updatedPatient;
	}

	@DeleteMapping("/carepulse/patients/{id}")
	public ResponseEntity<Map<String, String>> deletePatient(@PathVariable Long id) {

//    	Soft delete: Update the status of the patient to DELETED instead of deleting the record
		Patient deletedPatient = patientService.deletePatient(id);
		return ResponseEntity.ok(Map.of("message", "Patient deleted successfully", id.toString(),
				deletedPatient.getPatientId().toString()));
	}

}
