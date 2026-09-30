package com.carepulse.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.carepulse.service.AppointmentService;

@RestController
public class AppointmentController {

	private final AppointmentService appointmentService;
	
	public AppointmentController(AppointmentService appointmentService) {
		this.appointmentService = appointmentService;
	}
	
//	Method to test appointment service
	@GetMapping("/carepulse/appointments/test")
	public Map<String, Object> testAppointmentService() {
		Map<String, Object> response = new java.util.HashMap<>();
		response.put("message", "Appointment service is working fine");
		response.put("status", "success");
		response.put("data", appointmentService.getAppointmentMessage());
		return response;
	}
	
//	Method to get all appointments
	@GetMapping("/carepulse/appointments")
	public Map<String, Object> getAllAppointments() {
		Map<String, Object> response = new java.util.HashMap<>();
		response.put("message", "All appointments retrieved successfully");
		response.put("status", "success");
		response.put("data", appointmentService.getAllAppointments());
		return response;
	}

//	Method to get appointment by ID
	@GetMapping("/carepulse/appointments/{id}")
	public Map<String, Object> getAppointmentById(@PathVariable Long id) {
		Map<String, Object> response = new java.util.HashMap<>();
		response.put("message", "Appointment retrieved successfully");
		response.put("status", "success");
		response.put("data", appointmentService.getAppointmentById(id));
		return response;
	}
	
//	Method to get appointments by doctor ID
	@GetMapping("/carepulse/appointments/doctor/{doctorId}")
	public Map<String, Object> getAppointmentsByDoctorId(@PathVariable Long doctorId) {
		Map<String, Object> response = new java.util.HashMap<>();
		response.put("message", "Appointments retrieved successfully for doctor ID: " + doctorId);
		response.put("status", "success");
		response.put("data", appointmentService.getAppointmentsByDoctorId(doctorId));
		return response;
	}
	
//	Method to get appointments by patient ID
	@GetMapping("/carepulse/appointments/patient/{patientId}")
	public Map<String, Object> getAppointmentsByPatientId(@PathVariable Long patientId) {
		Map<String, Object> response = new java.util.HashMap<>();
		response.put("message", "Appointments retrieved successfully for patient ID: " + patientId);
		response.put("status", "success");
		response.put("data", appointmentService.getAppointmentsByPatientId(patientId));
		return response;
	}
	
//	Method to get appointments by hospital ID
	@GetMapping("/carepulse/appointments/hospital/{hospitalId}")
	public Map<String, Object> getAppointmentsByHospitalId(@PathVariable Long hospitalId) {
		Map<String, Object> response = new java.util.HashMap<>();
		response.put("message", "Appointments retrieved successfully for hospital ID: " + hospitalId);
		response.put("status", "success");
		response.put("data", appointmentService.getAppointmentsByHospitalId(hospitalId));
		return response;
	}
	
	

}
