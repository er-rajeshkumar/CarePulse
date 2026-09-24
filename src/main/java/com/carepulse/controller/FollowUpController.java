package com.carepulse.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.carepulse.dto.FollowUpCreateRequestDto;
import com.carepulse.dto.FollowUpDetailedResponseDto;
import com.carepulse.service.FollowUpService;

import jakarta.validation.Valid;

@RestController
public class FollowUpController {

	private final FollowUpService followUpService;

	public FollowUpController(FollowUpService followUpService) {
		this.followUpService = followUpService;
	}

	@GetMapping("/carepulse/followup/test")
	public String testFollowUpService() {
		return followUpService.getMessage();
	}
	
	@GetMapping("/carepulse/followup")
	public List<FollowUpDetailedResponseDto> getAllFollowUps() {
		return followUpService.getAllFollowUps();
	}
	
	@GetMapping("/carepulse/followup/{id}")
	public FollowUpDetailedResponseDto getFollowUpById(@PathVariable Long id) {
		return followUpService.getFollowUpById(id);
	}
	
	@GetMapping("/carepulse/followup/patientcase/{patientCaseId}")
	public List<FollowUpDetailedResponseDto> getFollowUpsByPatientCaseId(@PathVariable Long patientCaseId) {
		return followUpService.getFollowUpsByPatientCaseId(patientCaseId);
	}
	
	@GetMapping("/carepulse/followup/doctor/{doctorId}")
	public List<FollowUpDetailedResponseDto> getFollowUpsByDoctorId(@PathVariable Long doctorId) {
		return followUpService.getFollowUpsByDoctorId(doctorId);
	}
	
	@GetMapping("/carepulse/followup/patient/{patientId}")
	public List<FollowUpDetailedResponseDto> getFollowUpsByPatientId(@PathVariable Long patientId) {
		return followUpService.getFollowUpsByPatientId(patientId);
	}
	
	@GetMapping("/carepulse/followup/hospital/{hospitalId}")
	public List<FollowUpDetailedResponseDto> getFollowUpsByHospitalId(@PathVariable Long hospitalId) {
		return followUpService.getFollowUpsByHospitalId(hospitalId);
	}
	
	@PostMapping("/carepulse/followup")
	public FollowUpDetailedResponseDto createFollowUp(@Valid @RequestBody FollowUpCreateRequestDto followUpCreateRequestDto) {
		return followUpService.createFollowUp(followUpCreateRequestDto);
	}
	
	@PutMapping("/carepulse/followup/{id}")
	public FollowUpDetailedResponseDto updateFollowUp(@PathVariable Long id, @Valid @RequestBody FollowUpCreateRequestDto followUpCreateRequestDto) {
		return followUpService.updateFollowUp(id, followUpCreateRequestDto);
	}

	
}


