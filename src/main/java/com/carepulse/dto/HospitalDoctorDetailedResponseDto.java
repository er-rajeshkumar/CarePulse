package com.carepulse.dto;

public class HospitalDoctorDetailedResponseDto {

	private Long hospitalDoctorId;
	
	private Long doctorId;
	private String doctorName;
	private Long doctorSpecialization;
	private String doctorRegistrationNo;
	
	private Long hospitalId;
	private String hospitalName;
	private String hospitalAddress;
	private String hospitalCode;
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
	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	public Long getDoctorSpecialization() {
		return doctorSpecialization;
	}
	public void setDoctorSpecialization(Long doctorSpecialization) {
		this.doctorSpecialization = doctorSpecialization;
	}
	public String getDoctorRegistrationNo() {
		return doctorRegistrationNo;
	}
	public void setDoctorRegistrationNo(String doctorRegistrationNo) {
		this.doctorRegistrationNo = doctorRegistrationNo;
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
	public String getHospitalCode() {
		return hospitalCode;
	}
	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}
	
	
}
