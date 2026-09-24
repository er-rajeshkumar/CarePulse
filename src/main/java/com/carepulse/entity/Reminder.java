package com.carepulse.entity;

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
public class Reminder {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "REMINDER_ID")
	private Long reminderId;
	
	@ManyToOne
	@JoinColumn(name = "MEDICATION_ID", nullable = false)
	private Medication medication;
	
	@Column(name = "REMINDER_TIME")
	private LocalTime reminderTime;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "REMINDER_TYPE")
	private ReminderType reminderType = ReminderType.MEDICINE;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "REPEAT_TYPE")
	private RepeatType repeatType = RepeatType.DAILY;
	
	@Column(name = "REPEAT_DAYS")
	private String repeatDays;
	
	@Column(name = "REMINDER_MESSAGE")
	private String reminderMessage;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "STATUS")
	private Status status = Status.ACTIVE;
	
	@Column(name = "CREATED_AT")
	private LocalDateTime createdAt;
	
	@Column(name = "UPDATED_AT")
	private LocalDateTime updatedAt;

	public Long getReminderId() {
		return reminderId;
	}

	public void setReminderId(Long reminderId) {
		this.reminderId = reminderId;
	}

	public Medication getMedication() {
		return medication;
	}

	public void setMedication(Medication medication) {
		this.medication = medication;
	}

	public LocalTime getReminderTime() {
		return reminderTime;
	}

	public void setReminderTime(LocalTime reminderTime) {
		this.reminderTime = reminderTime;
	}

	public ReminderType getReminderType() {
		return reminderType;
	}

	public void setReminderType(ReminderType reminderType) {
		this.reminderType = reminderType;
	}

	public RepeatType getRepeatType() {
		return repeatType;
	}

	public void setRepeatType(RepeatType repeatType) {
		this.repeatType = repeatType;
	}

	public String getRepeatDays() {
		return repeatDays;
	}

	public void setRepeatDays(String repeatDays) {
		this.repeatDays = repeatDays;
	}

	public String getReminderMessage() {
		return reminderMessage;
	}

	public void setReminderMessage(String reminderMessage) {
		this.reminderMessage = reminderMessage;
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
