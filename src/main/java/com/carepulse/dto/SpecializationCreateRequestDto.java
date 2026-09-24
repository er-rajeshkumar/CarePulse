package com.carepulse.dto;

import com.carepulse.entity.Status;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;

public class SpecializationCreateRequestDto {

	@Column(name = "SPECIALIZATION_ID")
	private Long specializationId;

	@NotNull(message = "Specialization code cannot be null")
	@Column(name = "SPECIALIZATION_CODE")
	private String specializationCode;

	@NotNull(message = "Specialization name cannot be null")
	@Column(name = "SPECIALIZATION_NAME")
	private String specializationName;

	@Column(name = "SPECIALIZATION_DESCRIPTION")
	private String specializationDescription;

	@Enumerated(EnumType.STRING)
	@Column(name = "STATUS")
	private Status status;

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

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

}
