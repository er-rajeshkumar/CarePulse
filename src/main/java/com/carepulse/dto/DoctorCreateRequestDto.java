package com.carepulse.dto;
import java.time.LocalDateTime;

import com.carepulse.entity.Sex;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
public class DoctorCreateRequestDto {

	@NotBlank
	private String firstName;
	private String lastName;
	@NotBlank
	@Email
	private String email;
	@Size(min = 10,max = 13)
	private String phone;
	private Long specializationId;
	@NotBlank
	private String doctorRegistrationNo;
	private Sex sex;
	private LocalDateTime createdAt;



	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
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
	public Long getSpecializationId() {
		return specializationId;
	}
	public void setSpecializationId(Long specializationId) {
		this.specializationId = specializationId;
	}
	public String getDoctorRegistrationNo() {
		return doctorRegistrationNo;
	}
	public void setDoctorRegistrationNo(String doctorRegistrationNo) {
		this.doctorRegistrationNo = doctorRegistrationNo;
	}
	public Sex getSex() {
		return sex;
	}
	public void setSex(Sex sex) {
		this.sex = sex;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
}
