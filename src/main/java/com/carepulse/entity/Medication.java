package com.carepulse.entity;

import java.time.LocalDate;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Medication {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MEDICATION_ID", nullable = false)
	private Long MedicationId;
	
	@Column(name = "PATIENT_CASE_ID")
	private Long patientCaseId;
	
	@Column(name = "MEDICINE_ID")
	private Long medicineId;
	
	@Column(name = "DOCTOR_ID")
	private Long doctorId;
	
	@Column(name = "DOSAGE")
	private String dosage;
	
	@Column(name = "FREQUENCY")
	private String frequency;
	
	@Column(name = "ROUTE")
	private String route;
	
	@Column(name = "START_DATE")
	private LocalDate startDate;
	
	@Column(name = "END_DATE")
	private LocalDate endDate;
	
	@Column(name = "INSTRUCTIONS")
	private String instructions;
	
	@Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
	private Status status;
	
	@Column(name = "CREATED_AT")
	private Date createdAt;
	
	@Column(name = "UPDATED_AT")
	private Date updatedAt;

	public Long getMedicationId() {
		return MedicationId;
	}

	public void setMedicationId(Long medicationId) {
		MedicationId = medicationId;
	}

	public Long getPatientCaseId() {
		return patientCaseId;
	}

	public void setPatientCaseId(Long patientCaseId) {
		this.patientCaseId = patientCaseId;
	}

	public Long getMedicineId() {
		return medicineId;
	}

	public void setMedicineId(Long medicineId) {
		this.medicineId = medicineId;
	}

	public Long getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Long doctorId) {
		this.doctorId = doctorId;
	}

	public String getDosage() {
		return dosage;
	}

	public void setDosage(String dosage) {
		this.dosage = dosage;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public String getRoute() {
		return route;
	}

	public void setRoute(String route) {
		this.route = route;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public String getInstructions() {
		return instructions;
	}

	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	
}
