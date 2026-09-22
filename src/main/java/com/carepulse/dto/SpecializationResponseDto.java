package com.carepulse.dto;


import jakarta.persistence.Column;

public class SpecializationResponseDto {

	@Column(name = "SPECIALIZATION_ID")
	private Long specializationId;

	@Column(name = "SPECIALIZATION_CODE")
	private String specializationCode;

	@Column(name = "SPECIALIZATION_NAME")
	private String specializationName;

	@Column(name = "SPECIALIZATION_DESCRIPTION")
	private String specializationDescription;

	public Long getSpecializationId() {
		return specializationId;
	}

	public void setSpecializationId(Long specializationId) {
		this.specializationId = specializationId;
	}
	public String getSpecializationCode() {
		return specializationCode;
	}

	public void setSpecializationCode(String specializationCode) {
		this.specializationCode = specializationCode;
	}

	public String getSpecializationName() {
		return specializationName;
	}

	public void setSpecializationName(String specializationName) {
		this.specializationName = specializationName;
	}

	public String getSpecializationDescription() {
		return specializationDescription;
	}

	public void setSpecializationDescription(String specializationDescription) {
		this.specializationDescription = specializationDescription;
	}

}
