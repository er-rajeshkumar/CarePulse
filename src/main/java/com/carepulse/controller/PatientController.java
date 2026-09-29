package com.carepulse.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.carepulse.dto.PatientCreateRequestDto;
import com.carepulse.dto.PatientResponseDto;
import com.carepulse.dto.updateDto.PatientUpdateRequestDto;
import com.carepulse.entity.Patient;
import com.carepulse.service.PatientService;

import jakarta.validation.Valid;

@RestController
public class PatientController {

	private final PatientService patientService;

	public PatientController(PatientService patientService) {
		this.patientService = patientService;
	}

	@GetMapping("/carepulse/patients/test")
	public Map<String, Object> testPatientService() {
		Map<String, Object> response = new HashMap<>();
		response.put("message", "Patient service is working fine");
		response.put("status", "success");
		response.put("data", patientService.getPatientMessage());
		return response;
	}

	@GetMapping("/carepulse/patients")
	public Map<String, Object> getAllPatients() {
		Map<String, Object> response = new HashMap<>();
		response.put("message", "All patients retrieved successfully");
		response.put("status", "success");
		
		response.put("data", patientService.getAllPatients());
		return response;
	}

	@GetMapping("/carepulse/patients/active")
	public List<PatientResponseDto> getAllActivePatients() {
		return patientService.getAllPatientsByStatus();
	}


	@GetMapping("/carepulse/patients/{id}")
	public Map<String, Object> getPatientById(@PathVariable Long id) {

		PatientResponseDto patientdto = patientService.getPatientById(id);

		// Create a response map with the success message and patient ID
		Map<String, Object> response = new HashMap<>();
		response.put("message", "Patient retrieved successfully");
		response.put("status", "success");
		response.put("patientId", patientdto.getPatientId());
		response.put("patientDetails", patientdto);
		return response;
	}

	@PostMapping("/carepulse/patients")
	public Map<String, Object> addPatient(@Valid @RequestBody PatientCreateRequestDto request) {

		PatientResponseDto patientDto = patientService.addPatient(request);

		// Create a response map with the success message and patient ID
		Map<String, Object> response = new HashMap<>();
		response.put("message", "Patient added successfully");
		response.put("patientId", patientDto.getPatientId());
		response.put("patientDetails", patientDto);

		return response;
	}

	@PutMapping("/carepulse/patients/{id}")
	public Map<String, Object> updatePatient(@PathVariable Long id,
			@Valid @RequestBody PatientUpdateRequestDto request) {

		PatientResponseDto updatedPatient = patientService.updatePatient(id, request);

		// Create a response map with the success message and patient ID
		Map<String, Object> response = new HashMap<>();
		response.put("message", "Patient updated successfully");
		response.put("patientId", updatedPatient.getPatientId());

		//Add updated patient to response
		response.put("updatedPatient", updatedPatient);

		return response;
	}

	@DeleteMapping("/carepulse/patients/{id}")
	public Map<String, Object> deletePatient(@PathVariable Long id) {

//    	Soft delete: Update the status of the patient to DELETED instead of deleting the record
		Patient deletedPatient = patientService.deletePatient(id);
		Map<String, Object> response = new HashMap<>();
		response.put("message", "Patient deleted successfully");
		response.put("patientId", deletedPatient.getPatientId());
		response.put("status", "success");
		response.put("deletedPatient", deletedPatient);
		
		return response;
	}

}
