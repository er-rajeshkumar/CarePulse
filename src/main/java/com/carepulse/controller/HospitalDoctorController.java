package com.carepulse.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.carepulse.dto.HospitalDoctorCreateRequestDto;
import com.carepulse.dto.HospitalDoctorDetailedResponseDto;
import com.carepulse.dto.HospitalDoctorResponseDto;
import com.carepulse.service.HospitalDoctorService;

import jakarta.validation.Valid;

@RestController
public class HospitalDoctorController {

	private final HospitalDoctorService hospitalDoctorService;
	
	public HospitalDoctorController(HospitalDoctorService hospitalDoctorService) {
		this.hospitalDoctorService = hospitalDoctorService;
	}
	
	
//	Get List of all hospital-doctors 
	@GetMapping("/carepulse/hospital-doctor")
	public List<HospitalDoctorDetailedResponseDto> getAllHospitalDoctors() {
		return hospitalDoctorService.getAllHospitalDoctors();
	}
	
//	Get List of all active hospital-doctors 
	@GetMapping("/carepulse/hospital-doctor/active")
	public List<HospitalDoctorDetailedResponseDto> getAllActiveHospitalDoctors() {
		return hospitalDoctorService.getAllActiveHospitalDoctors();
	}
	
//	Get List of all hospital-doctors by hospitalId
	@GetMapping("/carepulse/hospital-doctor/hospital/{hospitalId}")
	public List<HospitalDoctorDetailedResponseDto> getAllHospitalDoctorsByHospitalId(@PathVariable Long hospitalId) {
		return hospitalDoctorService.getAllHospitalDoctorsByHospitalId(hospitalId);
	}
	
	//	Get List of all active hospital-doctors by hospitalId
	@GetMapping("/carepulse/hospital-doctor/active/hospital/{hospitalId}")
	public List<HospitalDoctorDetailedResponseDto> getAllActiveHospitalDoctorsByHospitalId(@PathVariable Long hospitalId) {
		return hospitalDoctorService.getAllActiveHospitalDoctorsByHospitalId(hospitalId);
	}
	
//	Get List of all hospital-doctor by doctorId
	@GetMapping("/carepulse/hospital-doctor/doctor/{doctorId}")
	public List<HospitalDoctorDetailedResponseDto> getAllHospitalDoctorsByDoctorId(@PathVariable Long doctorId) {
		return hospitalDoctorService.getAllHospitalDoctorsByDoctorId(doctorId);
	}
	
	//	Get List of all active hospital-doctor by doctorId
	@GetMapping("/carepulse/hospital-doctor/active/doctor/{doctorId}")
	public List<HospitalDoctorDetailedResponseDto> getAllActiveHospitalDoctorsByDoctorId(@PathVariable Long doctorId) {
		return hospitalDoctorService.getAllActiveHospitalDoctorsByDoctorId(doctorId);
	}
	
//	Add a new doctor to a hospital-doctor
	@PostMapping("/carepulse/hospital-doctor")
	public HospitalDoctorResponseDto addHospitalDoctor(@Valid @RequestBody HospitalDoctorCreateRequestDto requestDto) {
		return hospitalDoctorService.addHospitalDoctor(requestDto);
	}
}
