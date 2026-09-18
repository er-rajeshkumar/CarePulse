package com.carepulse.dto;

import com.carepulse.entity.Sex;
public class DoctorResponseDto {

	private String fullName;
	private Long specializationId;
	private Sex sex;
	private String email;
	private String phone;
	private String doctorRegistrationNo;

	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public Long getSpecializationId() {
		return specializationId;
	}
	public void setSpecializationId(Long specializationId) {
		this.specializationId = specializationId;
	}
	public Sex getSex() {
		return sex;
	}
	public void setSex(Sex sex) {
		this.sex = sex;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getDoctorRegistrationNo() {
		return doctorRegistrationNo;
	}
	public void setDoctorRegistrationNo(String doctorRegistrationNo) {
		this.doctorRegistrationNo = doctorRegistrationNo;
	}


}
