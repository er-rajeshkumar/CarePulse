package com.carepulse.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.carepulse.dto.MedicineTakeCreateRequestDto;
import com.carepulse.dto.MedicineTakeDetailedResponseDto;
import com.carepulse.entity.MedicineTakeStatus;
import com.carepulse.service.MedicineTakeService;

import jakarta.validation.Valid;

@RestController
public class MedicineTakeController {

	private final MedicineTakeService medicineTakeService;

	public MedicineTakeController(MedicineTakeService medicineTakeService) {
		this.medicineTakeService = medicineTakeService;
	}

	@GetMapping("/carepulse/medicine-take/message")
	public String getMessage() {
		return medicineTakeService.getMessage();
	}

	@GetMapping("/carepulse/medicine-take")
	public List<MedicineTakeDetailedResponseDto> getAllMedicineTakes() {
		return medicineTakeService.getAllMedicineTakes();
	}

	@GetMapping("/carepulse/medicine-take/{id}")
	public MedicineTakeDetailedResponseDto getMedicineTakeById(@PathVariable Long id) {
		return medicineTakeService.getMedicineTakeById(id);
	}

	@GetMapping("/carepulse/medicine-take/reminder/{reminderId}")
	public List<MedicineTakeDetailedResponseDto> getAllMedicineTakesByReminderId(@PathVariable Long reminderId) {
		return medicineTakeService.getAllMedicineTakesByReminderId(reminderId);
	}

	@GetMapping("/carepulse/medicine-take/patient/{patientId}")
	public List<MedicineTakeDetailedResponseDto> getAllMedicineTakesByPatientId(@PathVariable Long patientId) {
		return medicineTakeService.getAllMedicineTakesByPatientId(patientId);
	}

	@GetMapping("/carepulse/medicine-take/doctor/{doctorId}")
	public List<MedicineTakeDetailedResponseDto> getAllMedicineTakesByDoctorId(@PathVariable Long doctorId) {
		return medicineTakeService.getAllMedicineTakesByDoctorId(doctorId);
	}

	@GetMapping("/carepulse/medicine-take/status/{status}")
	public List<MedicineTakeDetailedResponseDto> getAllMedicineTakesByStatus(@PathVariable MedicineTakeStatus status) {
		return medicineTakeService.getAllMedicineTakesByStatus(status);
	}

	@PostMapping("/carepulse/medicine-take")
	public MedicineTakeDetailedResponseDto createMedicineTake(
			@RequestBody @Valid MedicineTakeCreateRequestDto requestDto) {
		return medicineTakeService.createMedicineTake(requestDto);
	}

	@PutMapping("/carepulse/medicine-take/{id}")
	public MedicineTakeDetailedResponseDto updateMedicineTake(@PathVariable Long id,
			@RequestBody @Valid MedicineTakeCreateRequestDto requestDto) {
		return medicineTakeService.updateMedicineTake(id, requestDto);
	}
}
