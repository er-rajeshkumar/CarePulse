package com.carepulse.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.carepulse.dto.ReminderCreateRequestDto;
import com.carepulse.dto.ReminderDetailedResponseDto;
import com.carepulse.entity.Doctor;
import com.carepulse.entity.Medication;
import com.carepulse.entity.Medicine;
import com.carepulse.entity.Patient;
import com.carepulse.entity.PatientCase;
import com.carepulse.entity.Reminder;
import com.carepulse.exception.ReminderException;
import com.carepulse.repository.ReminderRepository;

@Service
public class ReminderService {

	private final ReminderRepository reminderRepository;
	private final MedicationService medicationService;

	public ReminderService(ReminderRepository reminderRepository, MedicationService medicationService) {
		this.reminderRepository = reminderRepository;
		this.medicationService = medicationService;
	}

	public String getReminderMessage() {
		return "Reminder Service is working";
	}

//	Method to get all reminders 
	public List<ReminderDetailedResponseDto> getAllReminders() {
		List<Reminder> reminders = reminderRepository.findAll();
		List<ReminderDetailedResponseDto> reminderDTOs = new ArrayList<>();
		for (Reminder reminder : reminders) {
			reminderDTOs.add(convertToDTO(reminder));
		}
		return reminderDTOs;
	}

//	Method to get a reminder by ID
	public ReminderDetailedResponseDto getReminderById(Long reminderId) {
		Reminder reminder = reminderRepository.findById(reminderId)
				.orElseThrow(() -> new ReminderException("Reminder not found with id: " + reminderId));
		return convertToDTO(reminder);
	}

//	Method to get a reminder entity by ID
	public Reminder getReminderEntityById(Long reminderId) {
		return reminderRepository.findById(reminderId)
				.orElseThrow(() -> new ReminderException("Reminder not found with id: " + reminderId));
	}

//	Method to get reminders by patient ID
	public List<ReminderDetailedResponseDto> getRemindersByPatientId(Long patientId) {
		List<Reminder> reminders = reminderRepository.findAllByMedication_PatientCase_Patient_PatientId(patientId);
		List<ReminderDetailedResponseDto> reminderDTOs = new ArrayList<>();
		for (Reminder reminder : reminders) {
			reminderDTOs.add(convertToDTO(reminder));
		}
		return reminderDTOs;
	}

//	Method to get reminders by doctor ID
	public List<ReminderDetailedResponseDto> getRemindersByDoctorId(Long doctorId) {
		List<Reminder> reminders = reminderRepository.findAllByMedication_PatientCase_Doctor_DoctorId(doctorId);
		List<ReminderDetailedResponseDto> reminderDTOs = new ArrayList<>();
		for (Reminder reminder : reminders) {
			reminderDTOs.add(convertToDTO(reminder));
		}
		return reminderDTOs;
	}

//	Method to create a new reminder
	public ReminderDetailedResponseDto createReminder(ReminderCreateRequestDto dto) {
		Reminder reminder = convertToEntity(dto);
		Reminder savedReminder = reminderRepository.save(reminder);
		return convertToDTO(savedReminder);
	}

//	Method to update an existing reminder
	public ReminderDetailedResponseDto updateReminder(Long reminderId, ReminderCreateRequestDto dto) {
		Reminder existingReminder = reminderRepository.findById(reminderId)
				.orElseThrow(() -> new ReminderException("Reminder not found with id: " + reminderId));

		existingReminder.setReminderTime(dto.getReminderTime());
		existingReminder.setReminderType(dto.getReminderType());
		existingReminder.setRepeatType(dto.getRepeatType());
		existingReminder.setRepeatDays(dto.getRepeatDays());
		existingReminder.setReminderMessage(dto.getReminderMessage());
		existingReminder.setStatus(dto.getStatus());

		Medication medication = medicationService.getMedicationEntityById(dto.getMedicationId());
		existingReminder.setMedication(medication);

		Reminder updatedReminder = reminderRepository.save(existingReminder);
		return convertToDTO(updatedReminder);
	}

//	Helper method to convert a Reminder entity to a ReminderDTO
	public ReminderDetailedResponseDto convertToDTO(Reminder reminder) {
		ReminderDetailedResponseDto dto = new ReminderDetailedResponseDto();
		dto.setReminderId(reminder.getReminderId());

		Medication medication = reminder.getMedication();
		dto.setMedicationId(medication.getMedicationId());
		dto.setDosage(medication.getDosage());
		dto.setFrequency(medication.getFrequency());
		dto.setRoute(medication.getRoute());
		dto.setInstructions(medication.getInstructions());

		PatientCase patientCase = medication.getPatientCase();
		dto.setPatientCaseId(patientCase.getId());
		dto.setCaseTitle(patientCase.getCaseTitle());

		Patient patient = patientCase.getPatient();
		dto.setPatientId(patient.getPatientId());
		dto.setPatientFullName(patient.getFirstName() + " " + patient.getLastName());

		Medicine medicine = medication.getMedicine();
		dto.setMedicineId(medicine.getMedicineId());
		dto.setMedicineName(medicine.getMedicineName());
		dto.setStrength(medicine.getStrength());

		Doctor doctor = medication.getDoctor();
		dto.setDoctorId(doctor.getDoctorId());
		dto.setDoctorFullName(doctor.getFirstName() + " " + doctor.getLastName());

		dto.setMedicationId(medication.getMedicationId());
		dto.setReminderTime(reminder.getReminderTime());
		dto.setReminderType(reminder.getReminderType());
		dto.setRepeatType(reminder.getRepeatType());
		dto.setRepeatDays(reminder.getRepeatDays());
		dto.setReminderMessage(reminder.getReminderMessage());

		return dto;
	}

//	Helper method to convert a ReminderCreateRequestDto to a Reminder entity
	public Reminder convertToEntity(ReminderCreateRequestDto dto) {
		Reminder reminder = new Reminder();
		reminder.setReminderId(dto.getReminderId());
		Long medicationId = dto.getMedicationId();
		Medication medication = medicationService.getMedicationEntityById(medicationId);
		reminder.setMedication(medication);
		reminder.setReminderTime(dto.getReminderTime());
		reminder.setReminderType(dto.getReminderType());
		reminder.setRepeatType(dto.getRepeatType());
		reminder.setRepeatDays(dto.getRepeatDays());
		reminder.setReminderMessage(dto.getReminderMessage());

		return reminder;
	}
}
