package com.carepulse.dto;

import jakarta.validation.constraints.NotNull;

public class PatientCaseCreateRequestDto {

	@NotNull(message = "Hospital ID cannot be null")
	private Long hospitalId;
	@NotNull(message = "Patient ID cannot be null")
	private Long patientId;
	@NotNull(message = "Doctor ID cannot be null")
	private Long doctorId;

	@NotNull(message = "Case title cannot be null")
	private String caseTitle;
	private String diagnosis;

	private String admissionDate;
	private String dischargeDate;

	private String note;

	public Long getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(Long hospitalId) {
		this.hospitalId = hospitalId;
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

	public String getCaseTitle() {
		return caseTitle;
	}

	public void setCaseTitle(String caseTitle) {
		this.caseTitle = caseTitle;
	}

	public String getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}

	public String getAdmissionDate() {
		return admissionDate;
	}

	public void setAdmissionDate(String admissionDate) {
		this.admissionDate = admissionDate;
	}

	public String getDischargeDate() {
		return dischargeDate;
	}

	public void setDischargeDate(String dischargeDate) {
		this.dischargeDate = dischargeDate;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
}
