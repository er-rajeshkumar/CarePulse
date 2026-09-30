package com.carepulse.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.carepulse.dto.AppointmentCreateRequestDto;
import com.carepulse.dto.AppointmentResponseDto;
import com.carepulse.dto.updateDto.AppointmentUpdateRequestDto;
import com.carepulse.entity.Appointment;
import com.carepulse.entity.Doctor;
import com.carepulse.entity.Hospital;
import com.carepulse.entity.Patient;
import com.carepulse.entity.PatientCase;
import com.carepulse.exception.EntityNotFoundException;
import com.carepulse.repository.AppointmentRepository;
import com.carepulse.util.DateTimeUtil;
import com.carepulse.util.NameUtil;

import jakarta.transaction.Transactional;

@Service
public class AppointmentService {

	private final AppointmentRepository appointmentRepository;
	private final DoctorService doctorService;
	private final PatientService patientService;
	private final HospitalService hospitalService;
	private final PatientCaseService patientCaseService;
	
	public AppointmentService(AppointmentRepository appointmentRepository,
			DoctorService doctorService, PatientService patientService, HospitalService hospitalService,
			PatientCaseService patientCaseService) {
		this.appointmentRepository = appointmentRepository;
		this.doctorService = doctorService;
		this.patientService = patientService;
		this.hospitalService = hospitalService;
		this.patientCaseService = patientCaseService;
	}
	
	private static final Logger logger = LoggerFactory.getLogger(AppointmentService.class);
	public void testLog() {
		logger.trace("TRACE Log");
		logger.debug("DEBUG Log");
		logger.info("INFO Log");
		logger.warn("WARN Log");
		logger.error("ERROR Log");
	}
	
	public String getAppointmentMessage() {
		return "Appointment Service is working";
	}
	// Method to get all appointments from the database
	public List<AppointmentResponseDto> getAllAppointments() {
		logger.info("Fetching all Appointments from the database");
		List<Appointment> appointments = appointmentRepository.findAll();
		List<AppointmentResponseDto> appointmentDtos = new ArrayList<>();
		for (Appointment appointment : appointments) {
			appointmentDtos.add(mapToDto(appointment));
		}
		return appointmentDtos;
	}
	
	// Method to get appointment by id
	public AppointmentResponseDto getAppointmentById(Long appointmentId) {
		logger.info("Fetching Appointment with ID: {}", appointmentId);
		Appointment appointment = appointmentRepository.findById(appointmentId)
				.orElseThrow(() -> new EntityNotFoundException("Appointment not found with ID: " + appointmentId));
		return mapToDto(appointment);
	}
	

	// Method to get appointments by doctor id
	public List<AppointmentResponseDto> getAppointmentsByDoctorId(Long doctorId) {
		logger.info("Fetching Appointments for Doctor with ID: {}", doctorId);
		List<Appointment> appointments = appointmentRepository.findAllByDoctor_DoctorId(doctorId);
		List<AppointmentResponseDto> appointmentDtos = new ArrayList<>();
		for (Appointment appointment : appointments) {
			appointmentDtos.add(mapToDto(appointment));
		}
		return appointmentDtos;
	}
	
	// Method to get appointments by patient id
	public List<AppointmentResponseDto> getAppointmentsByPatientId(Long patientId) {
		logger.info("Fetching Appointments for Patient with ID: {}", patientId);
		List<Appointment> appointments = appointmentRepository.findAllByPatient_PatientId(patientId);
		List<AppointmentResponseDto> appointmentDtos = new ArrayList<>();
		for (Appointment appointment : appointments) {
			appointmentDtos.add(mapToDto(appointment));
		}
		return appointmentDtos;
	}
	
	// Method to get appointments by hospital id
	public List<AppointmentResponseDto> getAppointmentsByHospitalId(Long hospitalId) {
		logger.info("Fetching Appointments for Hospital with ID: {}", hospitalId);
		List<Appointment> appointments = appointmentRepository.findAllByHospital_HospitalId(hospitalId);
		List<AppointmentResponseDto> appointmentDtos = new ArrayList<>();
		for (Appointment appointment : appointments) {
			appointmentDtos.add(mapToDto(appointment));
		}
		return appointmentDtos;
	}
	
	
	// Method to add a new appointment
	@Transactional
	public AppointmentResponseDto addAppointment(AppointmentCreateRequestDto requestDto) {
		logger.info("Adding new Appointment");
		Appointment appointment = createDtoToEntity(requestDto);
		appointment.setCreatedAt(java.time.LocalDateTime.now());
		Appointment savedAppointment = appointmentRepository.save(appointment);
		return mapToDto(savedAppointment);
	}
	
	// Method to update an existing appointment
	@Transactional
	public AppointmentResponseDto updateAppointment(Long appointmentId, AppointmentUpdateRequestDto requestDto) {
		logger.info("Updating Appointment with ID: {}", appointmentId);
		Appointment existingAppointment = appointmentRepository.findById(appointmentId)
				.orElseThrow(() -> new EntityNotFoundException("Appointment not found with ID: " + appointmentId));
		
		// Update the existing appointment with new values
		updateDtoToEntity(existingAppointment, requestDto);
		existingAppointment.setUpdatedAt(LocalDateTime.now());
		
		Appointment updatedAppointment = appointmentRepository.save(existingAppointment);
		return mapToDto(updatedAppointment);
	}
	
	
	
	//Helper method to convert Appointment entity to AppointmentResponseDto
	private AppointmentResponseDto mapToDto(Appointment appointment) {
		AppointmentResponseDto responseDto = new AppointmentResponseDto();
		
		Doctor doctor = appointment.getDoctor();
		responseDto.setDoctorId(doctor.getDoctorId());
		responseDto.setDoctorName(NameUtil.getFullName(doctor.getFirstName(), doctor.getLastName()));
		responseDto.setDoctorSpecialization(doctor.getSpecializationId().toString());
		responseDto.setDoctorEmail(doctor.getEmail());
		responseDto.setDoctorPhone(doctor.getPhone());
		
		Patient patient = appointment.getPatient();
		responseDto.setPatientId(patient.getPatientId());
		responseDto.setPatientName(NameUtil.getFullName(patient.getFirstName(), patient.getLastName()));
		responseDto.setPatientEmail(patient.getEmail());
		responseDto.setPatientPhone(patient.getPhone());
		
		Hospital hospital = appointment.getHospital();
		responseDto.setHospitalId(hospital.getHospitalId());
		responseDto.setHospitalName(hospital.getHospitalName());
		responseDto.setHospitalAddress(hospital.getHospitalAddress());
		responseDto.setHospitalPhone(hospital.getHospitalPhone());
		responseDto.setHospitalEmail(hospital.getHospitalEmail());
		
		PatientCase patientCase = appointment.getPatientCase();
		if (patientCase != null) {
		    responseDto.setPatientCaseId(patientCase.getId());
		    responseDto.setPatientCaseCaseTitle(patientCase.getCaseTitle());
		    responseDto.setPatientCaseDiagnosis(patientCase.getDiagnosis());
		}
		
		responseDto.setAppointmentId(appointment.getAppointmentId());
		responseDto.setAppointmentDate(appointment.getAppointmentDate());
		responseDto.setStatus(appointment.getStatus());
		responseDto.setReason(appointment.getReason());
		responseDto.setNotes(appointment.getNotes());
		if(DateTimeUtil.isValidTimeRange(appointment.getStartTime(), appointment.getEndTime())) {
			responseDto.setStartTime(appointment.getStartTime());
			responseDto.setEndTime(appointment.getEndTime());
		} else {
			responseDto.setStartTime(appointment.getStartTime());
			responseDto.setEndTime(null);
		}
		
		return responseDto;
	}
	
	// Helper method to convert AppointmentCreateRequestDto to Appointment entity
	private Appointment createDtoToEntity(AppointmentCreateRequestDto requestDto) {
		Appointment appointment = new Appointment();

		Doctor doctor = doctorService.getDoctorEntityById(requestDto.getDoctorId());
		appointment.setDoctor(doctor);
		
		Hospital hospital = hospitalService.getHospitalEntityById(requestDto.getHospitalId());
		appointment.setHospital(hospital);

		if (requestDto.getPatientCaseId() != null) {
		    PatientCase patientCase =
		            patientCaseService.getPatientCaseEntityById(requestDto.getPatientCaseId());

		    appointment.setPatientCase(patientCase);
		}
		
		
		Patient patient = patientService.getPatientEntityById(requestDto.getPatientId());
		appointment.setPatient(patient);
		
		appointment.setAppointmentDate(requestDto.getAppointmentDate());
		appointment.setStartTime(requestDto.getStartTime());
		appointment.setEndTime(requestDto.getEndTime());
		appointment.setStatus(requestDto.getStatus());
		appointment.setReason(requestDto.getReason());
		appointment.setNotes(requestDto.getNotes());
		
		return appointment;
	}
	
	
	// Logic is not complete yet, but I will provide the helper method to convert AppointmentUpdateRequestDto to Appointment entity. 
	//You can use this method in your updateAppointment method to map the updated values from the request DTO to the existing appointment entity.
	// Helper method to convert AppointmentUpdateRequestDto to Appointment entity
	private Appointment updateDtoToEntity(Appointment appointment, AppointmentUpdateRequestDto requestDto) {
		
		if (requestDto.getDoctorId() != null) {
			Doctor doctor = doctorService.getDoctorEntityById(requestDto.getDoctorId());
			appointment.setDoctor(doctor);
		}
		
		if (requestDto.getHospitalId() != null) {
			Hospital hospital = hospitalService.getHospitalEntityById(requestDto.getHospitalId());
			appointment.setHospital(hospital);
		}
		
		if (requestDto.getPatientCaseId() != null) {
		    PatientCase patientCase = patientCaseService.getPatientCaseEntityById(requestDto.getPatientCaseId());
		    appointment.setPatientCase(patientCase);
		}
		
		if (requestDto.getPatientId() != null) {
			Patient patient = patientService.getPatientEntityById(requestDto.getPatientId());
			appointment.setPatient(patient);
		}
		
		if (requestDto.getAppointmentDate() != null) {
			appointment.setAppointmentDate(requestDto.getAppointmentDate());
		}
		
		if (requestDto.getStartTime() != null) {
			appointment.setStartTime(requestDto.getStartTime());
		}
		
		
		if (requestDto.getEndTime() != null) {
			appointment.setEndTime(requestDto.getEndTime());
		}
		
		if (requestDto.getStatus() != null) {
			appointment.setStatus(requestDto.getStatus());
		}
		
		if (requestDto.getReason() != null) {
			appointment.setReason(requestDto.getReason());
		}
		
		if (requestDto.getNotes() != null) {
			appointment.setNotes(requestDto.getNotes());
		}
		
		return appointment;
	}
	
}
