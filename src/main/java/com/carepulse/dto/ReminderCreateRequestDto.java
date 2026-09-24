package com.carepulse.dto;

import java.time.LocalDateTime;
import java.time.LocalTime;

import com.carepulse.entity.ReminderType;
import com.carepulse.entity.RepeatType;
import com.carepulse.entity.Status;

import jakarta.validation.constraints.NotNull;

public class ReminderCreateRequestDto {
	private Long reminderId;
	
	@NotNull(message = "Medication ID cannot be null")
	private Long medicationId;
	
	@NotNull(message = "Reminder time cannot be null")
	private LocalTime reminderTime;
	
	private ReminderType reminderType = ReminderType.MEDICINE;
	private RepeatType repeatType = RepeatType.DAILY;
	private String repeatDays;
	private String reminderMessage;
	private Status status = Status.ACTIVE;
	private LocalDateTime  createdAt;
	private LocalDateTime  updatedAt;
	public Long getReminderId() {
		return reminderId;
	}
	public void setReminderId(Long reminderId) {
		this.reminderId = reminderId;
	}
	
	
	
	public Long getMedicationId() {
		return medicationId;
	}
	public void setMedicationId(Long medicationId) {
		this.medicationId = medicationId;
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
	public LocalDateTime  getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime  createdAt) {
		this.createdAt = createdAt;
	}
	public LocalDateTime  getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(LocalDateTime  updatedAt) {
		this.updatedAt = updatedAt;
	}
}
