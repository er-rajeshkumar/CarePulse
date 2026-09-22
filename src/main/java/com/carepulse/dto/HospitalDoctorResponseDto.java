package com.carepulse.dto;

public class HospitalDoctorResponseDto {

	private Long hospitalDoctorId;
	private Long doctorId;

	private Long hospitalId;

	public Long getHospitalDoctorId() {
		return hospitalDoctorId;
	}

	public void setHospitalDoctorId(Long hospitalDoctorId) {
		this.hospitalDoctorId = hospitalDoctorId;
	}

	public Long getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Long doctorId) {
		this.doctorId = doctorId;
	}

	public Long getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(Long hospitalId) {
		this.hospitalId = hospitalId;
	}
}
