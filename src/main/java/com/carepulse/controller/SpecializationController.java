package com.carepulse.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.carepulse.dto.SpecializationCreateRequestDto;
import com.carepulse.dto.SpecializationResponseDto;
import com.carepulse.service.SpecializationService;

import jakarta.validation.Valid;

@RestController
public class SpecializationController {

	private final SpecializationService specializationService;
	
	public SpecializationController(SpecializationService specializationService) {
		this.specializationService = specializationService;
	}
	
	@GetMapping("/carepulse/specializations/message")
	public String getSpecializationMessage() {
		return specializationService.getSpecializationMessage();
	}
	
	@GetMapping("/carepulse/specializations")
	public List<SpecializationResponseDto> getAllSpecializations() {
		return specializationService.getAllSpecializations();
	}
	
	@GetMapping("/carepulse/specializations/active")
	public List<SpecializationResponseDto> getAllActiveSpecializations() {
		return specializationService.getAllActiveSpecializations();
	}
	
	@GetMapping("/carepulse/specializations/{id}")
	public SpecializationResponseDto getSpecializationById(@PathVariable Long id) {
		return specializationService.getSpecializationById(id);
	}
	
	@PostMapping("/carepulse/specializations")
	public SpecializationResponseDto addSpecialization(@Valid @RequestBody SpecializationCreateRequestDto specializationCreateRequestDto) {
		return specializationService.addSpecialization(specializationCreateRequestDto);
	}
	
	@PutMapping("/carepulse/specializations/{id}")
	public SpecializationResponseDto updateSpecialization(@PathVariable Long id, @Valid @RequestBody SpecializationCreateRequestDto specializationCreateRequestDto) {
		return specializationService.updateSpecialization(id, specializationCreateRequestDto);
	}
	
	@DeleteMapping("/carepulse/specializations/{id}")
	public SpecializationResponseDto deleteSpecialization(@PathVariable Long id) {
		return specializationService.deleteSpecialization(id);
	}
	
}
