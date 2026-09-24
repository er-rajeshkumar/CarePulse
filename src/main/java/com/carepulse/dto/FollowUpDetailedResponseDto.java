package com.carepulse.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import com.carepulse.entity.FollowUpStatus;

public class FollowUpDetailedResponseDto {

	private Long followUpId;

//	PateintCase ID
	private Long patientCaseId;
//	private Hospital hospital;
	private Long hospitalId;
	private String hospitalName;
	private String hospitalAddress;

//	private Patient patient;
	private Long patientId;
	private String patientFullName;
	private String patientEmail;
	private String patientPhone;

//	private Doctor doctor;
	private Long doctorId;
	private String doctorFullName;
	private String doctorEmail;

	private LocalDate followUpDate;
	private LocalTime followUpTime;
	private String purpose;
	private FollowUpStatus followUpStatus;
	private String notes;

	public Long getFollowUpId() {
		return followUpId;
	}

	public void setFollowUpId(Long followUpId) {
		this.followUpId = followUpId;
	}

	public Long getPatientCaseId() {
		return patientCaseId;
	}

	public void setPatientCaseId(Long patientCaseId) {
		this.patientCaseId = patientCaseId;
	}

	public Long getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(Long hospitalId) {
		this.hospitalId = hospitalId;
	}

	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public String getHospitalAddress() {
		return hospitalAddress;
	}

	public void setHospitalAddress(String hospitalAddress) {
		this.hospitalAddress = hospitalAddress;
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

	public String getPatientEmail() {
		return patientEmail;
	}

	public void setPatientEmail(String patientEmail) {
		this.patientEmail = patientEmail;
	}

	public String getPatientPhone() {
		return patientPhone;
	}

	public void setPatientPhone(String patientPhone) {
		this.patientPhone = patientPhone;
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

	public String getDoctorEmail() {
		return doctorEmail;
	}

	public void setDoctorEmail(String doctorEmail) {
		this.doctorEmail = doctorEmail;
	}

	public LocalDate getFollowUpDate() {
		return followUpDate;
	}

	public void setFollowUpDate(LocalDate followUpDate) {
		this.followUpDate = followUpDate;
	}

	public LocalTime getFollowUpTime() {
		return followUpTime;
	}

	public void setFollowUpTime(LocalTime followUpTime) {
		this.followUpTime = followUpTime;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public FollowUpStatus getFollowUpStatus() {
		return followUpStatus;
	}

	public void setFollowUpStatus(FollowUpStatus followUpStatus) {
		this.followUpStatus = followUpStatus;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

}
