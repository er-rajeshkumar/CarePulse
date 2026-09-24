package com.carepulse.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import com.carepulse.entity.FollowUpStatus;

public class FollowUpCreateRequestDto {

	private Long followUpId;
	private Long patientCaseId;
	private LocalDate followUpDate;
	private LocalTime followUpTime;
	private String purpose;
	private FollowUpStatus followUpStatus;
	private String notes;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	public Long getFollowUpId() {
		return followUpId;
	}

	public void setFollowUpId(Long followUpId) {
		this.followUpId = followUpId;
	}

	public Long getPatientCaseId() {
		return patientCaseId;
	}

	public void setPatientCaseId(Long patientCaseId) {
		this.patientCaseId = patientCaseId;
	}

	public LocalDate getFollowUpDate() {
		return followUpDate;
	}

	public void setFollowUpDate(LocalDate followUpDate) {
		this.followUpDate = followUpDate;
	}

	public LocalTime getFollowUpTime() {
		return followUpTime;
	}

	public void setFollowUpTime(LocalTime followUpTime) {
		this.followUpTime = followUpTime;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public FollowUpStatus getFollowUpStatus() {
		return followUpStatus;
	}

	public void setFollowUpStatus(FollowUpStatus followUpStatus) {
		this.followUpStatus = followUpStatus;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
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
