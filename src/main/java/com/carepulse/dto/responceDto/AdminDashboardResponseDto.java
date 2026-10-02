package com.carepulse.dto.responceDto;

public class AdminDashboardResponseDto {

    private Long totalPatients;
    private Long totalDoctors;
    private Long totalHospitals;

    private Long totalAppointments;

    private Long activeCases;

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
    
    
}
