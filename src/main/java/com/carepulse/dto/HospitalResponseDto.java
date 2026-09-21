package com.carepulse.dto;

import jakarta.persistence.Column;

public class HospitalResponseDto {

	@Column(name = "HOSPITAL_ID")
	private Long hospitalId;
	
	@Column(name = "HOSPITAL_CODE")
	private String hospitalCode;
	
	@Column(name = "HOSPITAL_NAME")
	private String hospitalName;
	
	@Column(name = "HOSPITAL_EMAIL")
	private String hospitalEmail;
	
	@Column(name = "HOSPITAL_PHONE")
	private String hospitalPhone;
	
	@Column(name = "ADDRESS")
	private String hospitalAddress;

	public Long getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(Long hospitalId) {
		this.hospitalId = hospitalId;
	}

	public String getHospitalCode() {
		return hospitalCode;
	}

	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}

	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public String getHospitalEmail() {
		return hospitalEmail;
	}

	public void setHospitalEmail(String hospitalEmail) {
		this.hospitalEmail = hospitalEmail;
	}

	public String getHospitalPhone() {
		return hospitalPhone;
	}

	public void setHospitalPhone(String hospitalPhone) {
		this.hospitalPhone = hospitalPhone;
	}

	public String getHospitalAddress() {
		return hospitalAddress;
	}

	public void setHospitalAddress(String hospitalAddress) {
		this.hospitalAddress = hospitalAddress;
	}
	
}
