package com.carepulse.dto;

import java.time.LocalDateTime;

import org.hibernate.validator.constraints.Length;

import com.carepulse.entity.Status;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class HospitalCreateRequestDto {

	
	private Long hospitalId;

	@NotNull(message = "Hospital code cannot be null")
	private String hospitalCode;

	@NotNull(message = "Hospital name cannot be null")
	private String hospitalName;

	@Email(message = "Invalid email format")
	@NotNull(message = "Hospital email cannot be null")
	private String hospitalEmail;

	@Length(min = 10, max = 15, message = "Hospital phone number must be between 10 and 15 digits")
	@Pattern(regexp = "\\d+", message = "Hospital phone number must contain only digits")
	private String hospitalPhone;


	private String hospitalAddress;

	@Enumerated(EnumType.STRING)
	private Status status;

	private LocalDateTime createdAt;

	private LocalDateTime updatedAt;

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

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

}
