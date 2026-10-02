package com.carepulse.dto.createDto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class HospitalPatientCreateRequestDto {
	@NotNull(message = "Hospital ID cannot be null")
	@NotBlank(message = "Hospital ID cannot be blank")
	@Min(value = 1, message = "Hospital ID must be greater than 0")
	private Long hospitalId;

	@NotNull(message = "Patient ID cannot be null")
	@NotBlank(message = "Patient ID cannot be blank")
	@Min(value = 1, message = "Patient ID must be greater than 0")
	private Long patientId;

	@NotBlank(message = "Hospital patient number cannot be blank")
	private String hospitalPatientNo;

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

	public String getHospitalPatientNo() {
		return hospitalPatientNo;
	}

	public void setHospitalPatientNo(String hospitalPatientNo) {
		this.hospitalPatientNo = hospitalPatientNo;
	}

}
