package com.carepulse.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class FollowUp {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "FOLLOW_UP_ID")
	private Long followUpId;

	@ManyToOne
	@JoinColumn(name = "PATIENT_CASE_ID")
	private PatientCase patientCase;

	@Column(name = "FOLLOW_UP_DATE")
	private LocalDate followUpDate;

	@Column(name = "FOLLOW_UP_TIME")
	private LocalTime followUpTime;

	@Column(name = "PURPOSE")
	private String purpose;

	@Enumerated(EnumType.STRING)
	@Column(name = "STATUS")
	private FollowUpStatus followUpStatus;

	@Column(name = "NOTES")
	private String notes;

	@Column(name = "CREATED_AT")
	private LocalDateTime createdAt;

	@Column(name = "UPDATED_AT")
	private LocalDateTime updatedAt;

	public Long getFollowUpId() {
		return followUpId;
	}

	public void setFollowUpId(Long followUpId) {
		this.followUpId = followUpId;
	}

	public PatientCase getPatientCase() {
		return patientCase;
	}

	public void setPatientCase(PatientCase patientCase) {
		this.patientCase = patientCase;
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
