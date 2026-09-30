package com.carepulse.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.carepulse.dto.AppointmentResponseDto;
import com.carepulse.entity.Appointment;
import com.carepulse.entity.Doctor;
import com.carepulse.entity.Hospital;
import com.carepulse.entity.Patient;
import com.carepulse.entity.PatientCase;
import com.carepulse.exception.EntityNotFoundException;
import com.carepulse.repository.AppointmentRepository;

@Service
public class AppointmentService {

	private final AppointmentRepository appointmentRepository;
	
	public AppointmentService(AppointmentRepository appointmentRepository) {
		this.appointmentRepository = appointmentRepository;
	}
	
	private static final Logger logger = LoggerFactory.getLogger(DoctorService.class);
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
	
	
	
	
	
	
	
	
	//Helper method to convert Appointment entity to AppointmentResponseDto
	private AppointmentResponseDto mapToDto(Appointment appointment) {
		AppointmentResponseDto responseDto = new AppointmentResponseDto();
		
		Doctor doctor = appointment.getDoctor();
		responseDto.setDoctorId(doctor.getDoctorId());
		responseDto.setDoctorName(doctor.getFirstName() + " " + doctor.getLastName());
		responseDto.setDoctorSpecialization(doctor.getSpecializationId().toString());
		responseDto.setDoctorEmail(doctor.getEmail());
		responseDto.setDoctorPhone(doctor.getPhone());
		
		Patient patient = appointment.getPatient();
		responseDto.setPatientId(patient.getPatientId());
		responseDto.setPatientName(patient.getFirstName() + " " + patient.getLastName());
		responseDto.setPatientEmail(patient.getEmail());
		responseDto.setPatientPhone(patient.getPhone());
		
		Hospital hospital = appointment.getHospital();
		responseDto.setHospitalId(hospital.getHospitalId());
		responseDto.setHospitalName(hospital.getHospitalName());
		responseDto.setHospitalAddress(hospital.getHospitalAddress());
		responseDto.setHospitalPhone(hospital.getHospitalPhone());
		responseDto.setHospitalEmail(hospital.getHospitalEmail());
		
		PatientCase patientCase = appointment.getPatientCase();
		responseDto.setPatientCaseId(patientCase.getId());
		responseDto.setPatientCaseDescription(patientCase.getDiagnosis());
		responseDto.setPatientCaseDiagnosis(patientCase.getDiagnosis());
		
		responseDto.setAppointmentId(appointment.getAppointmentId());
		responseDto.setAppointmentDate(appointment.getAppointmentDate());
		responseDto.setStatus(appointment.getStatus());
		responseDto.setReason(appointment.getReason());
		responseDto.setNotes(appointment.getNotes());
		
		responseDto.setStartTime(appointment.getStartTime());
		responseDto.setEndTime(appointment.getEndTime());
		return responseDto;
	}
}
