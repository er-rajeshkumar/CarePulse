package com.carepulse.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class HospitalDoctor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "HOSPITAL_DOCTOR_ID")
	private Long hospitalDoctorId;
	
//	@Column(name = "HOSPITAL_ID")
//	private Long hospitalId;
//	
//	@Column(name = "DOCTOR_ID")
//	private Long doctorId;
	
	@ManyToOne
	@JoinColumn(name = "HOSPITAL_ID", nullable = false)
	private Hospital hospital;

	@ManyToOne
	@JoinColumn(name = "DOCTOR_ID", nullable = false)
	private Doctor doctor;
	
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

	/**
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

	**/
	
	public LocalDate getJoinDate() {
		return joinDate;
	}

	public Hospital getHospital() {
		return hospital;
	}

	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
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
