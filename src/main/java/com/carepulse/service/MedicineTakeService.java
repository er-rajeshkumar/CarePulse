package com.carepulse.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.carepulse.dto.MedicineTakeCreateRequestDto;
import com.carepulse.dto.MedicineTakeDetailedResponseDto;
import com.carepulse.entity.Medication;
import com.carepulse.entity.MedicineTake;
import com.carepulse.entity.MedicineTakeStatus;
import com.carepulse.entity.PatientCase;
import com.carepulse.entity.Reminder;
import com.carepulse.exception.MedicineTakeException;
import com.carepulse.repository.MedicineTakeRepository;

@Service
public class MedicineTakeService {

	private static final Logger logger = LoggerFactory.getLogger(MedicineTakeService.class);

	private final MedicineTakeRepository medicineTakeRepository;
	private final ReminderService reminderService;
	private final MedicationService medicationService;

	public MedicineTakeService(MedicineTakeRepository medicineTakeRepository, ReminderService reminderService,
			MedicationService medicationService) {
		this.medicineTakeRepository = medicineTakeRepository;
		this.reminderService = reminderService;
		this.medicationService = medicationService;
	}

	// Method to get a message from the service
	public String getMessage() {
		return "Hello from MedicineTakeService!";
	}

	// Method to get all MedicineTake
	public List<MedicineTakeDetailedResponseDto> getAllMedicineTakes() {
		List<MedicineTake> medicineTakes = medicineTakeRepository.findAll();
		return convertToDtoList(medicineTakes);
	}

	// Method to get a MedicineTake by its ID
	public MedicineTakeDetailedResponseDto getMedicineTakeById(Long id) {
		MedicineTake medicineTake = medicineTakeRepository.findById(id)
				.orElseThrow(() -> new MedicineTakeException("MedicineTake not found with id: " + id));
		return convertToDto(medicineTake);
	}

	// Method to get all MedicineTake by Reminder ID
	public List<MedicineTakeDetailedResponseDto> getAllMedicineTakesByReminderId(Long reminderId) {
		List<MedicineTake> medicineTakes = medicineTakeRepository.findAllByReminder_ReminderId(reminderId);
		return convertToDtoList(medicineTakes);
	}

	// Method to get all MedicineTake by Patient ID
	public List<MedicineTakeDetailedResponseDto> getAllMedicineTakesByPatientId(Long patientId) {
		List<MedicineTake> medicineTakes = medicineTakeRepository
				.findAllByMedication_PatientCase_Patient_PatientId(patientId);

		return convertToDtoList(medicineTakes);
	}

	// Method to get all MedicineTake by Doctor ID
	public List<MedicineTakeDetailedResponseDto> getAllMedicineTakesByDoctorId(Long doctorId) {
		List<MedicineTake> medicineTakes = medicineTakeRepository.findAllByMedication_Doctor_DoctorId(doctorId);
		return convertToDtoList(medicineTakes);
	}

	// Method to get all MedicineTake by
	// Status('PENDING','TAKEN','MISSED','SKIPPED','SNOOZED')
	public List<MedicineTakeDetailedResponseDto> getAllMedicineTakesByStatus(MedicineTakeStatus status) {
		List<MedicineTake> medicineTakes = medicineTakeRepository.findAllByStatus(status);
		return convertToDtoList(medicineTakes);
	}

	// Method to create a new MedicineTake
	public MedicineTakeDetailedResponseDto createMedicineTake(MedicineTakeCreateRequestDto dto) {
		logger.info("Creating new MedicineTake for medication id: {}", dto.getMedicationId());
		MedicineTake medicineTake = convertToEntity(dto);
		medicineTake.setCreatedAt(LocalDateTime.now());
		medicineTake = medicineTakeRepository.save(medicineTake);
		logger.info("MedicineTake created with id: {}", medicineTake.getId());
		return convertToDto(medicineTake);
	}

	// Method to update an existing MedicineTake
	public MedicineTakeDetailedResponseDto updateMedicineTake(Long id, MedicineTakeCreateRequestDto dto) {
		logger.info("Updating MedicineTake with id: {}", id);
		MedicineTake existingMedicineTake = medicineTakeRepository.findById(id)
				.orElseThrow(() -> {
					logger.error("MedicineTake not found with id: {}", id);
					return new MedicineTakeException("MedicineTake not found with id: " + id);
				});

		existingMedicineTake.setScheduledTime(dto.getScheduledTime());
		existingMedicineTake.setActionTime(dto.getActionTime());
		existingMedicineTake.setStatus(dto.getStatus());
		existingMedicineTake.setNotes(dto.getNotes());

		Medication medication = medicationService.getMedicationEntityById(dto.getMedicationId());
		existingMedicineTake.setMedication(medication);

		Reminder reminder = reminderService.getReminderEntityById(dto.getReminderId());
		existingMedicineTake.setReminder(reminder);
		existingMedicineTake.setUpdatedAt(LocalDateTime.now());
		existingMedicineTake = medicineTakeRepository.save(existingMedicineTake);
		return convertToDto(existingMedicineTake);
	}

	// Helper method to convert a list of MedicineTake entities to a list of
	// MedicineTakeDetailedResponseDto
	private List<MedicineTakeDetailedResponseDto> convertToDtoList(List<MedicineTake> medicineTakes) {
		List<MedicineTakeDetailedResponseDto> dtoList = new ArrayList<>();
		for (MedicineTake medicineTake : medicineTakes) {
			dtoList.add(convertToDto(medicineTake));
		}
		return dtoList;
	}

	// Helper method to convert a MedicineTake entity to a
	// MedicineTakeDetailedResponseDto
	public MedicineTakeDetailedResponseDto convertToDto(MedicineTake medicineTake) {
		MedicineTakeDetailedResponseDto dto = new MedicineTakeDetailedResponseDto();
		dto.setMedicineTakeId(medicineTake.getId());

		Medication medication = medicineTake.getMedication();
		if (medication != null) {
			dto.setMedicationId(medication.getMedicationId());
			PatientCase patientCase = medication.getPatientCase();
			dto.setPatientCaseId(patientCase.getId());
			dto.setCaseTitle(patientCase.getCaseTitle());
			dto.setPatientId(patientCase.getPatient().getPatientId());
			dto.setPatientFullName(
					patientCase.getPatient().getFirstName() + " " + patientCase.getPatient().getLastName());
			dto.setDoctorId(patientCase.getDoctor().getDoctorId());
			dto.setDoctorFullName(patientCase.getDoctor().getFirstName() + " " + patientCase.getDoctor().getLastName());
		}
		Reminder reminderId = medicineTake.getReminder();
		if (reminderId != null) {
			dto.setReminderId(reminderId.getReminderId());
		}

		dto.setScheduledTime(medicineTake.getScheduledTime());
		dto.setActionTime(medicineTake.getActionTime());
		dto.setNotes(medicineTake.getNotes());
		return dto;
	}

	// Helper method to convert a MedicineTakeCreateRequestDto to a MedicineTake
	// entity
	public MedicineTake convertToEntity(MedicineTakeCreateRequestDto dto) {
		MedicineTake medicineTake = new MedicineTake();

		Medication medication = medicationService.getMedicationEntityById(dto.getMedicationId());
		medicineTake.setMedication(medication);

		Reminder reminder = reminderService.getReminderEntityById(dto.getReminderId());
		medicineTake.setReminder(reminder);

		medicineTake.setScheduledTime(dto.getScheduledTime());
		medicineTake.setActionTime(dto.getActionTime());
		medicineTake.setStatus(dto.getStatus());
		medicineTake.setNotes(dto.getNotes());

		return medicineTake;
	}
}
