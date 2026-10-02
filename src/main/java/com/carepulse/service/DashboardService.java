package com.carepulse.service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.carepulse.dto.responceDto.AdminDashboardResponseDto;
import com.carepulse.dto.responceDto.DoctorDashboardResponseDto;
import com.carepulse.dto.responceDto.HospitalDashboardResponseDto;
import com.carepulse.dto.responceDto.PatientDashboardResponseDto;
import com.carepulse.entity.Doctor;
import com.carepulse.entity.Hospital;
import com.carepulse.entity.Patient;
import com.carepulse.entity.PatientCase;
import com.carepulse.enums.CaseStatus;
import com.carepulse.exception.EntityNotFoundException;
import com.carepulse.repository.AppointmentRepository;
import com.carepulse.repository.HospitalDoctorRepository;
import com.carepulse.repository.PatientCaseRepository;
import com.carepulse.repository.PatientRepository;
import com.carepulse.util.NameUtil;

@Service
public class DashboardService {
	
	private final PatientRepository patientRepository;
	private final PatientCaseRepository patientCaseRepository;
	private final AppointmentRepository appointmentRepository;
	private final DoctorService doctorService;
	private final HospitalService hospitalService;
	private final HospitalDoctorRepository hospitalDoctorRepository;
	
	public DashboardService(

			PatientRepository patientRepository , 
			PatientCaseRepository patientCaseRepository , 
			AppointmentRepository appointmentRepository ,
			DoctorService doctorService ,
			HospitalService hospitalService,
			HospitalDoctorRepository hospitalDoctorRepository
			) {
		this.patientRepository = patientRepository;
		this.patientCaseRepository = patientCaseRepository;
		this.appointmentRepository = appointmentRepository;
		this.doctorService = doctorService;
		this.hospitalService = hospitalService;
		this.hospitalDoctorRepository = hospitalDoctorRepository;
	}
	
	public PatientDashboardResponseDto	getPatientDashboard(Long patientId) {
		Patient patient = patientRepository.findById(patientId).orElseThrow(() -> 
			new EntityNotFoundException("Patient not found with id: " + patientId));	
		PatientDashboardResponseDto responseDto = mapToPatientDashboardResponseDto(patient);
		responseDto.setPatientId(patientId);
		return responseDto;
	}

	public DoctorDashboardResponseDto getDoctorDashboard(Long doctorId) {
		DoctorDashboardResponseDto responseDto = mapToDoctorDashboardResponseDto(doctorId);
		return responseDto;
	}

	public HospitalDashboardResponseDto getHospitalDashboard(Long hospitalId) {
		
		return mapToHospitalDashboardResponseDto(hospitalId);
	}

	public AdminDashboardResponseDto getAdminDashboard() {
		return new AdminDashboardResponseDto();
	}
	
	
//	Helper methods to map all data to response DTOs
	private PatientDashboardResponseDto mapToPatientDashboardResponseDto(Patient patient) {
		PatientDashboardResponseDto responseDto = new PatientDashboardResponseDto();
		
		responseDto.setPatientName(NameUtil.getFullName(patient.getFirstName(), patient.getMiddleName() ,patient.getLastName()));
		responseDto.setActiveCasesCount(patientCaseRepository.countByPatient_PatientIdAndCaseStatus(patient.getPatientId(), CaseStatus.ACTIVE));
		responseDto.setAppointmentCount(appointmentRepository.countByPatient_PatientId(patient.getPatientId()));
		PatientCase latestCase =
		        patientCaseRepository
		                .findFirstByPatient_PatientIdAndCaseStatus(
		                		patient.getPatientId(),
		                        CaseStatus.ACTIVE)
		                .orElse(null);
		if (latestCase != null) {
			responseDto.setPrimaryDoctorName(NameUtil.getFullName(latestCase.getDoctor().getFirstName(), latestCase.getDoctor().getLastName()));
			responseDto.setPrimaryHospitalName(latestCase.getHospital().getHospitalName());
			
		}
		return responseDto;
	}
	
//	Helper methods to map all data to response DTOs
	private DoctorDashboardResponseDto mapToDoctorDashboardResponseDto(Long doctorId) {
		Doctor doctor = doctorService.getDoctorEntityById(doctorId);	
		
		DoctorDashboardResponseDto responseDto = new DoctorDashboardResponseDto();
		responseDto.setDoctorId(doctorId);
		responseDto.setDoctorName(NameUtil.getFullName(doctor.getFirstName(),doctor.getLastName()));
		responseDto.setActivePatientCount(patientCaseRepository.countDistinctByDoctor_DoctorIdAndCaseStatus(doctorId, CaseStatus.ACTIVE));
		responseDto.setAppointmentCountToday(appointmentRepository.countByDoctor_DoctorIdAndAppointmentDate(doctorId, java.time.LocalDate.now()));
		responseDto.setActiveCaseCount(patientCaseRepository.countByDoctor_DoctorIdAndCaseStatus(doctorId, CaseStatus.ACTIVE));
		return responseDto;
	}

//	Helper methods to map all data to response DTOs
	private HospitalDashboardResponseDto mapToHospitalDashboardResponseDto(Long hospitalId) {
		HospitalDashboardResponseDto responseDto = new HospitalDashboardResponseDto();
		Hospital hospital = hospitalService.getHospitalEntityById(hospitalId);
		responseDto.setHospitalId(hospitalId);
		responseDto.setHospitalName(hospital.getHospitalName());
		responseDto.setDoctorCount(hospitalDoctorRepository.countByHospital_HospitalId(hospitalId));
		responseDto.setPatientCount(patientCaseRepository.countDistinctByHospital_HospitalId(hospitalId));
		responseDto.setActiveCaseCount(patientCaseRepository.countByHospital_HospitalIdAndCaseStatus(hospitalId, CaseStatus.ACTIVE));
		responseDto.setAppointmentCountToday(appointmentRepository.countByHospital_HospitalIdAndAppointmentDate(hospitalId, LocalDate.now()));
		return responseDto;
	}
	
	
}
