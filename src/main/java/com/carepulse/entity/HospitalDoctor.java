package com.carepulse.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class HospitalDoctor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "HOSPITAL_DOCTOR_ID")
	private Long hospitalDoctorId;
	
	@Column(name = "HOSPITAL_ID")
	private Long hospitalId;
	
	@Column(name = "DOCTOR_ID")
	private Long doctorId;
	
	@Column(name = "JOIN_DATE")
	private LocalDate joinDate;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "STATUS")
	private Status status;

	public Long getHospitalDoctorId() {
		return hospitalDoctorId;
	}

	public void setHospitalDoctorId(Long hospitalDoctorId) {
		this.hospitalDoctorId = hospitalDoctorId;
	}

	public Long getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(Long hospitalId) {
		this.hospitalId = hospitalId;
	}

	public Long getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Long doctorId) {
		this.doctorId = doctorId;
	}

	public LocalDate getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(LocalDate joinDate) {
		this.joinDate = joinDate;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
}
