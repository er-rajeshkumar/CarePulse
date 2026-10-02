package com.carepulse.dto.responceDto;

public class HospitalDashboardResponseDto {
    private Long hospitalId;
    private String hospitalName;

    private Long doctorCount;
    private Long patientCount;

    private Long activeCaseCount;

    private Long appointmentCountToday;

	public Long getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(Long hospitalId) {
		this.hospitalId = hospitalId;
	}

	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public Long getDoctorCount() {
		return doctorCount;
	}

	public void setDoctorCount(Long doctorCount) {
		this.doctorCount = doctorCount;
	}

	public Long getPatientCount() {
		return patientCount;
	}

	public void setPatientCount(Long patientCount) {
		this.patientCount = patientCount;
	}

	public Long getActiveCaseCount() {
		return activeCaseCount;
	}

	public void setActiveCaseCount(Long activeCaseCount) {
		this.activeCaseCount = activeCaseCount;
	}

	public Long getAppointmentCountToday() {
		return appointmentCountToday;
	}

	public void setAppointmentCountToday(Long appointmentCountToday) {
		this.appointmentCountToday = appointmentCountToday;
	}
    
    

}
