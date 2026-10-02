package com.carepulse.dto.createDto;

import java.time.LocalDate;
import java.time.LocalTime;

import com.carepulse.enums.AppointmentStatus;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class AppointmentCreateRequestDto {

private Long appointmentId;

	@NotNull(message = "Patient ID cannot be null")
	@Positive(message = "Patient ID must be a positive number")
	private Long patientId;
	
	private Long doctorId;
	
	@NotNull(message = "Hospital ID cannot be null")
	@Positive(message = "Hospital ID must be a positive number")
	private Long hospitalId;
	
	private Long patientCaseId;
	
	private LocalDate appointmentDate;
	
	@NotNull(message = "Start time cannot be null")
	private LocalTime startTime;
	
	private LocalTime endTime;
	
	private AppointmentStatus status;
	
	private String reason;
	
	private String notes;

	public Long getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(Long appointmentId) {
		this.appointmentId = appointmentId;
	}

	public Long getPatientId() {
		return patientId;
	}

	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}

	public Long getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Long doctorId) {
		this.doctorId = doctorId;
	}

	public Long getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(Long hospitalId) {
		this.hospitalId = hospitalId;
	}

	public Long getPatientCaseId() {
		return patientCaseId;
	}

	public void setPatientCaseId(Long patientCaseId) {
		this.patientCaseId = patientCaseId;
	}

	public LocalDate getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(LocalDate appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	public LocalTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}

	public AppointmentStatus getStatus() {
		return status;
	}

	public void setStatus(AppointmentStatus status) {
		this.status = status;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
	

}
