package com.carepulse.dto;

import java.time.LocalDate;

public class MedicationResponseDto {
	
	private Long medicationId;

	private Long patientCaseId;
	private String caseTitle;

	private Long patientId;
	private String patientFullName;

	private Long doctorId;
	private String doctorFullName;

	private Long medicineId;
	private String medicineName;
	private String strength;

	private String dosage;
	private String frequency;
	private String route;
	private LocalDate startDate;
	private LocalDate endDate;
	private String instructions;
	public Long getMedicationId() {
		return medicationId;
	}
	public void setMedicationId(Long medicationId) {
		this.medicationId = medicationId;
	}
	public Long getPatientCaseId() {
		return patientCaseId;
	}
	public void setPatientCaseId(Long patientCaseId) {
		this.patientCaseId = patientCaseId;
	}
	public String getCaseTitle() {
		return caseTitle;
	}
	public void setCaseTitle(String caseTitle) {
		this.caseTitle = caseTitle;
	}
	public Long getPatientId() {
		return patientId;
	}
	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}
	public String getPatientFullName() {
		return patientFullName;
	}
	public void setPatientFullName(String patientFullName) {
		this.patientFullName = patientFullName;
	}
	public Long getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(Long doctorId) {
		this.doctorId = doctorId;
	}
	public String getDoctorFullName() {
		return doctorFullName;
	}
	public void setDoctorFullName(String doctorFullName) {
		this.doctorFullName = doctorFullName;
	}
	public Long getMedicineId() {
		return medicineId;
	}
	public void setMedicineId(Long medicineId) {
		this.medicineId = medicineId;
	}
	public String getMedicineName() {
		return medicineName;
	}
	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}
	public String getStrength() {
		return strength;
	}
	public void setStrength(String strength) {
		this.strength = strength;
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
}