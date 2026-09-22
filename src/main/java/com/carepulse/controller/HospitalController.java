package com.carepulse.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.carepulse.dto.HospitalCreateRequestDto;
import com.carepulse.dto.HospitalResponseDto;
import com.carepulse.service.HospitalService;

import jakarta.validation.Valid;

@RestController
public class HospitalController {

	private final HospitalService hospitalService;

	public HospitalController(HospitalService hospitalService) {
		this.hospitalService = hospitalService;
	}
	@GetMapping("/carepulse/hospital/message")
	public String getHospitalMessage() {
		return hospitalService.getHospitalMessage();
	}

	@GetMapping("/carepulse/hospital")
	public List<HospitalResponseDto> getAllHospitals() {
		return hospitalService.getAllHospitals();
	}

	@GetMapping("/carepulse/hospital/{id}")
	public HospitalResponseDto getHospitalById(@PathVariable Long id) {
		return hospitalService.getHospitalById(id);
	}

	@GetMapping("/carepulse/hospital/active")
	public List<HospitalResponseDto> getAllHospitalsByStatus() {
		return hospitalService.getAllHospitalsByStatus();
	}

	@PostMapping("/carepulse/hospital")
	public HospitalResponseDto addHospital(@Valid @RequestBody HospitalCreateRequestDto hospitalCreateRequestDto) {
		return hospitalService.addHospital(hospitalCreateRequestDto);
	}

	@PutMapping("/carepulse/hospital/{id}")
	public HospitalResponseDto updateHospital(@PathVariable Long id, @Valid @RequestBody HospitalCreateRequestDto hospitalCreateRequestDto) {
		return hospitalService.updateHospital(id, hospitalCreateRequestDto);
	}

	@DeleteMapping("/carepulse/hospital/{id}")
	public HospitalResponseDto deleteHospital(@PathVariable Long id) {
		return hospitalService.deleteHospital(id);
	}
}
