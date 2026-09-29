package com.carepulse.dto;

import java.time.LocalDateTime;

import com.carepulse.entity.MedicineTakeStatus;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class MedicineTakeCreateRequestDto {
	private Long id;

	@NotNull(message = "Medication ID cannot be null")
	@Min(value = 1, message = "Medication ID must be greater than 0")
	private Long medicationId;

	@NotNull(message = "Reminder ID cannot be null")
	@Min(value = 1, message = "Reminder ID must be greater than 0")
	private Long reminderId;
	private LocalDateTime scheduledTime;
	private LocalDateTime actionTime;

	private MedicineTakeStatus status = MedicineTakeStatus.PENDING;
	private String notes;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getMedicationId() {
		return medicationId;
	}

	public void setMedicationId(Long medicationId) {
		this.medicationId = medicationId;
	}

	public Long getReminderId() {
		return reminderId;
	}

	public void setReminderId(Long reminderId) {
		this.reminderId = reminderId;
	}

	public LocalDateTime getScheduledTime() {
		return scheduledTime;
	}

	public void setScheduledTime(LocalDateTime scheduledTime) {
		this.scheduledTime = scheduledTime;
	}

	public LocalDateTime getActionTime() {
		return actionTime;
	}

	public void setActionTime(LocalDateTime actionTime) {
		this.actionTime = actionTime;
	}

	public MedicineTakeStatus getStatus() {
		return status;
	}

	public void setStatus(MedicineTakeStatus status) {
		this.status = status;
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
