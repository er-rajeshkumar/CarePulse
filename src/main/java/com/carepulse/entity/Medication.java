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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Medication {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MEDICATION_ID", nullable = false)
	private Long medicationId;

	@ManyToOne
	@JoinColumn(name = "PATIENT_CASE_ID")
	private PatientCase patientCase;

	@ManyToOne
	@JoinColumn(name = "MEDICINE_ID")
	private Medicine medicine;

	@ManyToOne
	@JoinColumn(name = "DOCTOR_ID")
	private Doctor doctor;

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
		return medicationId;
	}

	public void setMedicationId(Long medicationId) {
		this.medicationId = medicationId;
	}

	public PatientCase getPatientCase() {
		return patientCase;
	}

	public void setPatientCase(PatientCase patientCase) {
		this.patientCase = patientCase;
	}

	public Medicine getMedicine() {
		return medicine;
	}

	public void setMedicine(Medicine medicine) {
		this.medicine = medicine;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
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