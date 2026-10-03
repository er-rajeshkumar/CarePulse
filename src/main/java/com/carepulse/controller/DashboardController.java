package com.carepulse.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carepulse.service.DashboardService;

@RestController
@RequestMapping("/carepulse/dashboard")
public class DashboardController {

	private final DashboardService dashboardService;

	public DashboardController(DashboardService dashboardService) {
		this.dashboardService = dashboardService;
	}

	@GetMapping("/patient/{patientId}")
	public Map<String, Object> getPatientDashboard(@PathVariable Long patientId) {
		Map<String, Object> response = new java.util.HashMap<>();
		response.put("message", "Patient dashboard retrieved successfully");
		response.put("status", "success");
		response.put("data", dashboardService.getPatientDashboard(patientId));
		return response;
	}


	@GetMapping("/doctor/{doctorId}")
	public Map<String, Object> getDoctorDashboard(@PathVariable Long doctorId) {
		Map<String, Object> response = new java.util.HashMap<>();
		response.put("message", "Doctor dashboard retrieved successfully");
		response.put("status", "success");
		response.put("data", dashboardService.getDoctorDashboard(doctorId));
		return response;
	}

	@GetMapping("/hospital/{hospitalId}")
	public Map<String, Object> getHospitalDashboard(@PathVariable Long hospitalId) {
		Map<String, Object> response = new java.util.HashMap<>();
		response.put("message", "Hospital dashboard retrieved successfully");
		response.put("status", "success");
		response.put("data", dashboardService.getHospitalDashboard(hospitalId));
		return response;
	}

	@GetMapping("/admin")
	public Map<String, Object> getAdminDashboard() {
		Map<String, Object> response = new java.util.HashMap<>();
		response.put("message", "Admin dashboard retrieved successfully");
		response.put("status", "success");
		response.put("data", dashboardService.getAdminDashboard());
		return response;
	}
}
