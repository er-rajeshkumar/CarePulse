package com.carepulse.dto;

public class HospitalPatientDetailedResponseDto {

	private Long hospitalPatientId;
	private String hospitalPatientNumber;

	private Long hospitalId;
	private String hospitalName;
	private String hospitalAddress;

	private Long patientId;
	private String patientFullName;
	private String patientGender;
	private String patientDateOfBirth;
	private String patientEmail;
	private String patientPhoneNumber;
	private String patientAddress;

	public Long getHospitalPatientId() {
		return hospitalPatientId;
	}

	public void setHospitalPatientId(Long hospitalPatientId) {
		this.hospitalPatientId = hospitalPatientId;
	}

	public String getHospitalPatientNumber() {
		return hospitalPatientNumber;
	}

	public void setHospitalPatientNumber(String hospitalPatientNumber) {
		this.hospitalPatientNumber = hospitalPatientNumber;
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

	public String getPatientGender() {
		return patientGender;
	}

	public void setPatientGender(String patientGender) {
		this.patientGender = patientGender;
	}

	public String getPatientDateOfBirth() {
		return patientDateOfBirth;
	}

	public void setPatientDateOfBirth(String patientDateOfBirth) {
		this.patientDateOfBirth = patientDateOfBirth;
	}

	public String getPatientEmail() {
		return patientEmail;
	}

	public void setPatientEmail(String patientEmail) {
		this.patientEmail = patientEmail;
	}

	public String getPatientPhoneNumber() {
		return patientPhoneNumber;
	}

	public void setPatientPhoneNumber(String patientPhoneNumber) {
		this.patientPhoneNumber = patientPhoneNumber;
	}

	public String getPatientAddress() {
		return patientAddress;
	}

	public void setPatientAddress(String patientAddress) {
		this.patientAddress = patientAddress;
	}
}
