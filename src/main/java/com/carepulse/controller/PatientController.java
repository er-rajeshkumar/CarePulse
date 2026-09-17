package com.carepulse.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
//import org.hibernate.mapping.Map;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/carepulse/patients/test")
    public String testPatientService() {
        return patientService.getPatientMessage();
    }
    

    @GetMapping("/carepulse/getAllPatients")
    public List<Patient> getAllPatients() {
        return patientService.getAllPatients();
    }
    
//    @GetMapping("/api/patients/{id}")
//    public Patient getPatientById(@PathVariable Long id) {
//		return patientService.getPatientById(id);
//	}
    
    
    @GetMapping("/carepulse/getPatientById/{id}")
    public ResponseEntity<?> getPatientById(@PathVariable Long id) {

    	PatientResponseDto  patientdto = patientService.getPatientById(id);

        return ResponseEntity.ok(patientdto);
    }
    
    @PostMapping("/carepulse/addPatient")
    public ResponseEntity<Map<String, String>> addPatient
    ( @Valid @RequestBody PatientCreateRequestDto request) 
    {
		
		Patient savedPatient = patientService.addPatient(request);

		// Create a response map with the success message and patient ID
		Map<String, String> response = new HashMap<>();
		response.put("message", "Patient added successfully");
		response.put("patientId", savedPatient.getPatientId().toString());

		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
    
    @PutMapping("/carepulse/updatePatient/{id}")
    public ResponseEntity<Map<String, String>> updatePatient(
			@PathVariable Long id,
			@Valid @RequestBody PatientCreateRequestDto request) {
		
		Patient updatedPatient = patientService.updatePatient(id, request);

		// Create a response map with the success message and patient ID
		Map<String, String> response = new HashMap<>();
		response.put("message", "Patient updated successfully");
		response.put("patientId", updatedPatient.getPatientId().toString());

		return new ResponseEntity<>(response, HttpStatus.OK);
	}
    
    
    
}
