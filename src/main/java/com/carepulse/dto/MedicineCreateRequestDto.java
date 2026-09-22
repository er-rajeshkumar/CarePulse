package com.carepulse.dto;

import java.util.Date;

import com.carepulse.entity.Status;

import jakarta.validation.constraints.NotNull;

public class MedicineCreateRequestDto {

	@NotNull(message = "Medicine ID cannot be null")
	private String medicineName;

	@NotNull(message = "Brand Name cannot be null")
	private String brandName;

	@NotNull(message = "Form cannot be null")
	private String form;

	@NotNull(message = "Strength cannot be null")
	private String strength;

	private String description;

	private Status status = Status.ACTIVE;

	private Date createdAt;
	private Date updatedAt;
	public Long getMedicineId() {
		return medicineId;
	}
	public void setMedicineId(Long medicineId) {
		this.medicineId = medicineId;
	}
	public String getMedicineName() {
		return medicineName;
	}
	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public String getForm() {
		return form;
	}
	public void setForm(String form) {
		this.form = form;
	}
	public String getStrength() {
		return strength;
	}
	public void setStrength(String strength) {
		this.strength = strength;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

}
