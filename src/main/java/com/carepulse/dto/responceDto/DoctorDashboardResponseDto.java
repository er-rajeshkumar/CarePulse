package com.carepulse.dto.responceDto;

public class DoctorDashboardResponseDto {

    private Long doctorId;
    private String doctorName;

    private Long activePatientCount;

    private Long appointmentCountToday;

    private Long activeCaseCount;

	public Long getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Long doctorId) {
		this.doctorId = doctorId;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public Long getActivePatientCount() {
		return activePatientCount;
	}

	public void setActivePatientCount(Long activePatientCount) {
		this.activePatientCount = activePatientCount;
	}

	public Long getAppointmentCountToday() {
		return appointmentCountToday;
	}

	public void setAppointmentCountToday(Long appointmentCountToday) {
		this.appointmentCountToday = appointmentCountToday;
	}

	public Long getActiveCaseCount() {
		return activeCaseCount;
	}

	public void setActiveCaseCount(Long activeCaseCount) {
		this.activeCaseCount = activeCaseCount;
	}


}
