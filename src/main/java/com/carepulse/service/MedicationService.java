package com.carepulse.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.carepulse.dto.MedicationCreateRequestDto;
import com.carepulse.dto.MedicationResponseDto;
import com.carepulse.entity.Doctor;
import com.carepulse.entity.Medication;
import com.carepulse.entity.Medicine;
import com.carepulse.entity.PatientCase;
import com.carepulse.exception.DoctorNotFoundException;
import com.carepulse.exception.MedicineException;
import com.carepulse.exception.PatientCaseException;
import com.carepulse.repository.MedicationRepository;


@Service
public class MedicationService {

	private final MedicationRepository medicationRepository;
	
	private final DoctorService doctorService;
	private final PatientService patientService;
	private final PatientCaseService patientCaseService;
	private final MedicineService medicineService;
	
	
	
	public MedicationService(MedicationRepository medicationRepository, DoctorService doctorService, PatientService patientService, PatientCaseService patientCaseService, MedicineService medicineService) {
		this.medicationRepository = medicationRepository;
		this.doctorService = doctorService;
		this.patientService = patientService;
		this.patientCaseService = patientCaseService;
		this.medicineService = medicineService;
	}
	
	public String getMessage() {
		return "Medication Service is working!";
	}
	
//	Method to get all medications from the database
	public List<MedicationResponseDto> getAllMedications(){
		List<Medication> medications = medicationRepository.findAll();
		List<MedicationResponseDto> medicationResponseDtos = new ArrayList<>();
		for(Medication medication : medications) {
			MedicationResponseDto dto = mapToDto(medication);
			medicationResponseDtos.add(dto);
		}
		return medicationResponseDtos;
	}
	
//	Method to get medication by ID
	public MedicationResponseDto getMedicationById(Long medicationId) {
		Medication medication = medicationRepository.findById(medicationId).orElseThrow(() -> new RuntimeException("Medication not found with ID: " + medicationId));
		return mapToDto(medication);
	}
	
//	Method to get Medication entity by ID
	public Medication getMedicationEntityById(Long medicationId) {
		return medicationRepository.findById(medicationId).orElseThrow(() -> new RuntimeException("Medication not found with ID: " + medicationId));
	}
	
//	Method to get all medications by patient case ID
	public List<MedicationResponseDto> getAllMedicationsByPatientCaseId(Long patientCaseId){
		List<Medication> medications = medicationRepository.findAllByPatientCase_Id(patientCaseId);
		List<MedicationResponseDto> medicationResponseDtos = new ArrayList<>();
		for(Medication medication : medications) {
			MedicationResponseDto dto = mapToDto(medication);
			medicationResponseDtos.add(dto);
		}
		return medicationResponseDtos;
	}
	
//	Method to get all medications by doctor ID
	public List<MedicationResponseDto> getAllMedicationsByDoctorId(Long doctorId){
		List<Medication> medications = medicationRepository.findAllByDoctor_DoctorId(doctorId);
		List<MedicationResponseDto> medicationResponseDtos = new ArrayList<>();
		for(Medication medication : medications) {
			MedicationResponseDto dto = mapToDto(medication);
			medicationResponseDtos.add(dto);
		}
		return medicationResponseDtos;
	}
	
//	Method to create a new medication
	public MedicationResponseDto createMedication(MedicationCreateRequestDto dto) {
		Medication medication = mapToEntiy(dto);
		//check if the patient case, medicine, and doctor exist
		if(!patientCaseService.checkPatientCaseExistsById(dto.getPatientCaseId())) {
			throw new PatientCaseException("Patient case not found with ID: " + dto.getPatientCaseId());
		}
		if(!medicineService.isMedicineExistsById(dto.getMedicineId())) {
			throw new MedicineException("Medicine not found with ID: " + dto.getMedicineId());
		}
		if(!doctorService.isDoctorExistsById(dto.getDoctorId())) {
			throw new DoctorNotFoundException("Doctor not found with ID: " + dto.getDoctorId());
		}
		medication.setCreatedAt(java.util.Date.from(java.time.Instant.now()));
		Medication savedMedication = medicationRepository.save(medication);
		return mapToDto(savedMedication);
	}
	
//	Method to update an existing medication
	public MedicationResponseDto updateMedication(Long medicationId, MedicationCreateRequestDto dto) {
		Medication existingMedication = medicationRepository.findById(medicationId).orElseThrow(() -> new RuntimeException("Medication not found with ID: " + medicationId));
		existingMedication.setPatientCase(patientCaseService.getPatientCaseEntityById(dto.getPatientCaseId()));
		existingMedication.setMedicine(medicineService.getMedicineEntityById(dto.getMedicineId()));
		existingMedication.setDoctor(doctorService.getDoctorEntityById(dto.getDoctorId()));
		existingMedication.setDosage(dto.getDosage());
		existingMedication.setFrequency(dto.getFrequency());
		existingMedication.setRoute(dto.getRoute());
		existingMedication.setStartDate(dto.getStartDate());
		existingMedication.setEndDate(dto.getEndDate());
		existingMedication.setInstructions(dto.getInstructions());
		existingMedication.setStatus(dto.getStatus());
		existingMedication.setUpdatedAt(java.util.Date.from(java.time.Instant.now()));
		Medication updatedMedication = medicationRepository.save(existingMedication);
		return mapToDto(updatedMedication);
	}
	
//	Helper method to convert Medication entity to MedicationResponseDto
	public MedicationResponseDto mapToDto(Medication medication) {
		MedicationResponseDto dto = new MedicationResponseDto();
		
		dto.setMedicationId(medication.getMedicationId());
//		Patient Case details
		PatientCase patientCase = medication.getPatientCase();
		dto.setPatientCaseId(patientCase.getId());
		dto.setPatientId(patientCase.getPatient().getPatientId());
		dto.setPatientFullName(patientCase.getPatient().getFirstName() + " " + patientCase.getPatient().getLastName());
		dto.setCaseTitle(patientCase.getCaseTitle());
		
//		Medicine details
		Medicine medicine = medication.getMedicine();
		dto.setMedicineId(medicine.getMedicineId());
		dto.setMedicineName(medicine.getMedicineName());
		dto.setStrength(medicine.getStrength());
		
//		Doctor details
		Doctor doctor = medication.getDoctor();
		dto.setDoctorFullName(doctor.getFirstName() + " " + doctor.getLastName());
		dto.setDoctorId(doctor.getDoctorId());
		
		dto.setDosage(medication.getDosage());
		dto.setFrequency(medication.getFrequency());
		dto.setRoute(medication.getRoute());
		dto.setStartDate(medication.getStartDate());
		dto.setEndDate(medication.getEndDate());
		dto.setInstructions(medication.getInstructions());
		return dto;
	}
	
//	Helper method to map MedicationCreateRequestDto to Medication entity
	public Medication mapToEntiy(MedicationCreateRequestDto dto) {
		Medication medication = new Medication();
//		medication.setPatientCaseId(dto.getPatientCaseId());
//		medication.setMedicineId(dto.getMedicineId());
//		medication.setDoctorId(dto.getDoctorId());
		medication.setPatientCase(patientCaseService.getPatientCaseEntityById(dto.getPatientCaseId()));
		medication.setMedicine(medicineService.getMedicineEntityById(dto.getMedicineId()));
		medication.setDoctor(doctorService.getDoctorEntityById(dto.getDoctorId()));
		
		medication.setDosage(dto.getDosage());
		medication.setFrequency(dto.getFrequency());
		medication.setRoute(dto.getRoute());
		medication.setStartDate(dto.getStartDate());
		medication.setEndDate(dto.getEndDate());
		medication.setInstructions(dto.getInstructions());
		medication.setStatus(dto.getStatus());
		return medication;
	}
}