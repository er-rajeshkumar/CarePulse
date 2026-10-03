package com.carepulse.dto.responceDto;

import java.util.List;

public class AdminDashboardResponseDto {

    private Long totalPatients;
    private Long totalDoctors;
    private Long totalHospitals;
    private Long totalAppointments;
    private Long activeCases;

    private List<StatusWisePatientCountDto> statusWisePatientCounts;
    private List<HospitalCountDto> hospitalWiseDoctorCounts;
    private List<HospitalCountDto> hospitalWisePatientCounts;
    

	public Long getTotalPatients() {
		return totalPatients;
	}

	public void setTotalPatients(Long totalPatients) {
		this.totalPatients = totalPatients;
	}

	public Long getTotalDoctors() {
		return totalDoctors;
	}

	public void setTotalDoctors(Long totalDoctors) {
		this.totalDoctors = totalDoctors;
	}

	public Long getTotalHospitals() {
		return totalHospitals;
	}

	public void setTotalHospitals(Long totalHospitals) {
		this.totalHospitals = totalHospitals;
	}

	public Long getTotalAppointments() {
		return totalAppointments;
	}

	public void setTotalAppointments(Long totalAppointments) {
		this.totalAppointments = totalAppointments;
	}

	public Long getActiveCases() {
		return activeCases;
	}

	public void setActiveCases(Long activeCases) {
		this.activeCases = activeCases;
	}

	public List<StatusWisePatientCountDto> getStatusWisePatientCounts() {
		return statusWisePatientCounts;
	}

	public void setStatusWisePatientCounts(List<StatusWisePatientCountDto> statusWisePatientCounts) {
		this.statusWisePatientCounts = statusWisePatientCounts;
	}

	public List<HospitalCountDto> getHospitalWiseDoctorCounts() {
		return hospitalWiseDoctorCounts;
	}

	public void setHospitalWiseDoctorCounts(List<HospitalCountDto> hospitalWiseDoctorCounts) {
		this.hospitalWiseDoctorCounts = hospitalWiseDoctorCounts;
	}

	public List<HospitalCountDto> getHospitalWisePatientCounts() {
		return hospitalWisePatientCounts;
	}

	public void setHospitalWisePatientCounts(List<HospitalCountDto> hospitalWisePatientCounts) {
		this.hospitalWisePatientCounts = hospitalWisePatientCounts;
	}


}
