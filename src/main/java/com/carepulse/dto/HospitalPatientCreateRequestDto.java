package com.carepulse.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class HospitalPatientCreateRequestDto {
    @NotNull(message = "Hospital ID cannot be null")
    private Long hospitalId;

    @NotNull(message = "Patient ID cannot be null")
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
