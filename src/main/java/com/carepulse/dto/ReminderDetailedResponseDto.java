package com.carepulse.dto;

import java.time.LocalTime;

import com.carepulse.entity.ReminderType;
import com.carepulse.entity.RepeatType;
import com.carepulse.entity.Status;

public class ReminderDetailedResponseDto {

	private Long reminderId;

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
	private String instructions;

	private LocalTime reminderTime;
	private ReminderType reminderType = ReminderType.MEDICINE;
	private RepeatType repeatType = RepeatType.DAILY;
	private String repeatDays = "All Days";
	private String reminderMessage;
	private Status status = Status.ACTIVE;

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

	public String getInstructions() {
		return instructions;
	}

	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}

	public LocalTime getReminderTime() {
		return reminderTime;
	}

	public void setReminderTime(LocalTime reminderTime) {
		this.reminderTime = reminderTime;
	}

	public ReminderType getReminderType() {
		return reminderType;
	}

	public void setReminderType(ReminderType reminderType) {
		this.reminderType = reminderType;
	}

	public RepeatType getRepeatType() {
		return repeatType;
	}

	public void setRepeatType(RepeatType repeatType) {
		this.repeatType = repeatType;
	}

	public String getRepeatDays() {
		return repeatDays;
	}

	public void setRepeatDays(String repeatDays) {
		this.repeatDays = repeatDays;
	}

	public String getReminderMessage() {
		return reminderMessage;
	}

	public void setReminderMessage(String reminderMessage) {
		this.reminderMessage = reminderMessage;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
}
