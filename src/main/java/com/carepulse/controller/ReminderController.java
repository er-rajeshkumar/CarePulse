package com.carepulse.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.carepulse.dto.ReminderCreateRequestDto;
import com.carepulse.dto.ReminderDetailedResponseDto;
import com.carepulse.service.ReminderService;

import jakarta.validation.Valid;

@RestController
public class ReminderController {

	private final ReminderService reminderService;
	
	public ReminderController(ReminderService reminderService) {
		this.reminderService = reminderService;
	}
	
	@GetMapping("/carepulse/reminder/test")
	public String testReminderService() {
		return reminderService.getReminderMessage();
	}
	
	@GetMapping("/carepulse/reminder")
	public List<ReminderDetailedResponseDto> getAllReminders() {
		return reminderService.getAllReminders();
	}
	
	@GetMapping("/carepulse/reminder/{reminderId}")
	public ReminderDetailedResponseDto getReminderById(@PathVariable Long reminderId) {
		return reminderService.getReminderById(reminderId);
	}
	
	@GetMapping("/carepulse/reminder/patient/{patientId}")
	public List<ReminderDetailedResponseDto> getRemindersByPatientId(@PathVariable Long patientId) {
		return reminderService.getRemindersByPatientId(patientId);
	}
	
	@GetMapping("/carepulse/reminder/doctor/{doctorId}")
	public List<ReminderDetailedResponseDto> getRemindersByDoctorId(@PathVariable Long doctorId) {
		return reminderService.getRemindersByDoctorId(doctorId);
	}
	
	@PostMapping("/carepulse/reminder")
	public ReminderDetailedResponseDto createReminder(@RequestBody @Valid ReminderCreateRequestDto reminderCreateRequestDto) {
		return reminderService.createReminder(reminderCreateRequestDto);
	}
	
	@PutMapping("/carepulse/reminder/{reminderid}")
	public ReminderDetailedResponseDto createReminderForMedication(@PathVariable Long medicationId, @RequestBody @Valid ReminderCreateRequestDto reminderCreateRequestDto) {
		return reminderService.updateReminder(medicationId, reminderCreateRequestDto);
	}
	
}
