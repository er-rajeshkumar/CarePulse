package com.carepulse.dto;

import java.time.LocalDateTime;

public class MedicineTakeDetailedResponseDto {

	private Long medicineTakeId;
	private Long reminderId;

	private Long medicationId;
	private Long patientCaseId;

	private String caseTitle;

	private Long patientId;
	private String patientFullName;
	private Long doctorId;
	private String doctorFullName;

	private LocalDateTime scheduledTime;
	private LocalDateTime actionTime;
	private String notes;

	public Long getMedicineTakeId() {
		return medicineTakeId;
	}

	public void setMedicineTakeId(Long medicineTakeId) {
		this.medicineTakeId = medicineTakeId;
	}

	public Long getReminderId() {
		return reminderId;
	}

	public void setReminderId(Long reminderId) {
		this.reminderId = reminderId;
	}

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

	public LocalDateTime getScheduledTime() {
		return scheduledTime;
	}

	public void setScheduledTime(LocalDateTime scheduledTime) {
		this.scheduledTime = scheduledTime;
	}

	public LocalDateTime getActionTime() {
		return actionTime;
	}

	public void setActionTime(LocalDateTime localDateTime) {
		this.actionTime = localDateTime;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

}
