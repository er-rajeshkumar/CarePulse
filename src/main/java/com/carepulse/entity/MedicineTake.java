package com.carepulse.entity;

import java.time.LocalDateTime;

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
public class MedicineTake {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MEDICINE_TAKE_ID")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "MEDICATION_ID")
	private Medication medication;

	@ManyToOne
	@JoinColumn(name = "REMINDER_ID")
	private Reminder reminder;

	@Column(name = "SCHEDULED_TIME")
	private LocalDateTime scheduledTime;

	@Column(name = "ACTION_TIME")
	private LocalDateTime actionTime;

	@Enumerated(EnumType.STRING)
	@Column(name = "STATUS")
	private MedicineTakeStatus status;

	@Column(name = "NOTES")
	private String notes;

	@Column(name = "CREATED_AT")
	private LocalDateTime createdAt;

	@Column(name = "UPDATED_AT")
	private LocalDateTime updatedAt;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Medication getMedication() {
		return medication;
	}

	public void setMedication(Medication medication) {
		this.medication = medication;
	}

	public Reminder getReminder() {
		return reminder;
	}

	public void setReminder(Reminder reminder) {
		this.reminder = reminder;
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
