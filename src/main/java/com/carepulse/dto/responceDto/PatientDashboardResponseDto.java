package com.carepulse.dto.responceDto;

import java.time.LocalDate;

public class PatientDashboardResponseDto {

    private Long patientId;
    private String patientName;

    private Long activeCasesCount;
    private Long appointmentCount;

    private String primaryDoctorName;
    private String primaryHospitalName;

    private LocalDate nextAppointmentDate;

	public Long getPatientId() {
		return patientId;
	}

	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public Long getActiveCasesCount() {
		return activeCasesCount;
	}

	public void setActiveCasesCount(Long activeCasesCount) {
		this.activeCasesCount = activeCasesCount;
	}

	public Long getAppointmentCount() {
		return appointmentCount;
	}

	public void setAppointmentCount(Long appointmentCount) {
		this.appointmentCount = appointmentCount;
	}

	public String getPrimaryDoctorName() {
		return primaryDoctorName;
	}

	public void setPrimaryDoctorName(String primaryDoctorName) {
		this.primaryDoctorName = primaryDoctorName;
	}

	public String getPrimaryHospitalName() {
		return primaryHospitalName;
	}

	public void setPrimaryHospitalName(String primaryHospitalName) {
		this.primaryHospitalName = primaryHospitalName;
	}

	public LocalDate getNextAppointmentDate() {
		return nextAppointmentDate;
	}

	public void setNextAppointmentDate(LocalDate nextAppointmentDate) {
		this.nextAppointmentDate = nextAppointmentDate;
	}
    
    
}
