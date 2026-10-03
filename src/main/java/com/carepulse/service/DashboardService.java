package com.carepulse.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.carepulse.dto.responceDto.AdminDashboardResponseDto;
import com.carepulse.dto.responceDto.DoctorDashboardResponseDto;
import com.carepulse.dto.responceDto.HospitalCountDto;
import com.carepulse.dto.responceDto.HospitalDashboardResponseDto;
import com.carepulse.dto.responceDto.PatientDashboardResponseDto;
import com.carepulse.dto.responceDto.StatusWisePatientCountDto;
import com.carepulse.entity.Doctor;
import com.carepulse.entity.Hospital;
import com.carepulse.entity.Patient;
import com.carepulse.entity.PatientCase;
import com.carepulse.enums.CaseStatus;
import com.carepulse.enums.Status;
import com.carepulse.exception.EntityNotFoundException;
import com.carepulse.repository.AppointmentRepository;
import com.carepulse.repository.DoctorRepository;
import com.carepulse.repository.HospitalDoctorRepository;
import com.carepulse.repository.HospitalPatientRepository;
import com.carepulse.repository.HospitalRepository;
import com.carepulse.repository.PatientCaseRepository;
import com.carepulse.repository.PatientRepository;
import com.carepulse.util.NameUtil;




@Service
@Transactional(readOnly = true)
public class DashboardService {

    private final HospitalRepository hospitalRepository;

	private final PatientRepository patientRepository;
	private final PatientCaseRepository patientCaseRepository;
	private final AppointmentRepository appointmentRepository;
	private final DoctorRepository doctorRepository;
	private final DoctorService doctorService;
	private final HospitalService hospitalService;
	private final HospitalDoctorRepository hospitalDoctorRepository;
	private final HospitalPatientRepository hospitalPatientRepository;
	
	public DashboardService(

			PatientRepository patientRepository ,
			PatientCaseRepository patientCaseRepository ,
			AppointmentRepository appointmentRepository ,
			DoctorRepository doctorRepository ,
			DoctorService doctorService ,
			HospitalService hospitalService,
			HospitalDoctorRepository hospitalDoctorRepository ,
			HospitalRepository hospitalRepository ,
			HospitalPatientRepository hospitalPatientRepository
			) {
		this.patientRepository = patientRepository;
		this.patientCaseRepository = patientCaseRepository;
		this.appointmentRepository = appointmentRepository;
		this.doctorRepository = doctorRepository;
		this.doctorService = doctorService;
		this.hospitalService = hospitalService;
		this.hospitalDoctorRepository = hospitalDoctorRepository;
		this.hospitalRepository = hospitalRepository;
		this.hospitalPatientRepository = hospitalPatientRepository;
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
		return mapToAdminDashboardResponseDto();
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

//	Helper methods to map all data to response DTOs
	private AdminDashboardResponseDto mapToAdminDashboardResponseDto() {
		AdminDashboardResponseDto responseDto = new AdminDashboardResponseDto();
		responseDto.setTotalPatients(patientRepository.count());
		List<Object[]> statusWisePatientCount = patientRepository.getPatientCountByStatus();
		List<Object[]> hospitalWisePatientCount = hospitalPatientRepository.getPatientCountByHospital();
		List<Object[]> hospitalWiseDcotorCount = hospitalDoctorRepository.getDoctorCountByHospital();
		responseDto.setStatusWisePatientCounts(convertToStatusWisePatientCounts(statusWisePatientCount));
		responseDto.setHospitalWisePatientCounts(convertToHospitalWisePatientCounts(hospitalWisePatientCount));
		responseDto.setHospitalWiseDoctorCounts(convertToHospitalWiseDoctorCounts(hospitalWiseDcotorCount));
		
		responseDto.setTotalDoctors(doctorRepository.count());
		responseDto.setTotalHospitals(hospitalRepository.count());
		responseDto.setTotalAppointments(appointmentRepository.count());
		responseDto.setActiveCases(patientCaseRepository.countByCaseStatus(CaseStatus.ACTIVE));
		return responseDto;
	}
	
//	Helper method to convert List<Object[]> result = patientRepository.getPatientCountByStatus(); 
	private List<StatusWisePatientCountDto> convertToStatusWisePatientCounts(List<Object[]> result) {
		List<StatusWisePatientCountDto> statusWisePatientCounts = new java.util.ArrayList<>();
		for (Object[] statusCount : result) {
			Status status = (Status) statusCount[0];
		    Long count = (Long) statusCount[1];
		    if(count == null) {
		    	count = 0L;
		    }
		    if(status == null) {
		    	status = Status.INACTIVE;
		    }
		    statusWisePatientCounts.add(new StatusWisePatientCountDto(status.toString(), count));
		}
		return statusWisePatientCounts;
	}
	
//	Helper method to convert List<Object[]> result = doctorRepository.getDoctorCountByHospital(); 
	private List<HospitalCountDto> convertToHospitalWiseDoctorCounts(List<Object[]> result) {
		List<HospitalCountDto> hospitalWiseDoctorCounts = new java.util.ArrayList<>();
		for (Object[] hospitalCount : result) {
			Long hospitalID = (Long) hospitalCount[0];
			String hospitalName = (String) hospitalCount[1];
		    Long count = (Long) hospitalCount[2];
		    hospitalWiseDoctorCounts.add(new HospitalCountDto(hospitalID,hospitalName, count));
		}
		return hospitalWiseDoctorCounts;
	}
	
	
//	Helper method to convert List<Object[]> result = patientRepository.getPatientCountByHospital(); 
	private List<HospitalCountDto> convertToHospitalWisePatientCounts(List<Object[]> result) {
		List<HospitalCountDto> hospitalWisePatientCounts = new java.util.ArrayList<>();
		for (Object[] hospitalCount : result) {
			Long hospitalID = (Long) hospitalCount[0];
			String hospitalName = (String) hospitalCount[1];
		    Long count = (Long) hospitalCount[2];
		    hospitalWisePatientCounts.add(new HospitalCountDto(hospitalID,hospitalName, count));
		}
		return hospitalWisePatientCounts;
	}
}
