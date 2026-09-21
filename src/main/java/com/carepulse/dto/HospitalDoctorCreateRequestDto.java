package com.carepulse.dto;

import jakarta.validation.constraints.NotNull;

public class HospitalDoctorCreateRequestDto {

	@NotNull(message = "Hospital ID cannot be null")
	private Long hospitalId;
	
	@NotNull(message = "Doctor ID cannot be null")
	private Long doctorId;

	public Long getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(Long hospitalId) {
		this.hospitalId = hospitalId;
	}

	public Long getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Long doctorId) {
		this.doctorId = doctorId;
	}
}
