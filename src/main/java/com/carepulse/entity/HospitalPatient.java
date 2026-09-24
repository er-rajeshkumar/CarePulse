package com.carepulse.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class HospitalPatient {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "HOSPITAL_PATIENT_ID")
	private Long hospitalPatientId;

	@Column(name = "HOSPITAL_PATIENT_NO", nullable = false)
	private String hospitalPatientNo;

	@ManyToOne
	@JoinColumn(name = "HOSPITAL_ID", nullable = false)
	private Hospital hospital;

	@ManyToOne
	@JoinColumn(name = "PATIENT_ID", nullable = false)
	private Patient patient;

	public Long getHospitalPatientId() {
		return hospitalPatientId;
	}

	public void setHospitalPatientId(Long hospitalPatientId) {
		this.hospitalPatientId = hospitalPatientId;
	}

	public String getHospitalPatientNo() {
		return hospitalPatientNo;
	}

	public void setHospitalPatientNo(String hospitalPatientNo) {
		this.hospitalPatientNo = hospitalPatientNo;
	}

	public Hospital getHospital() {
		return hospital;
	}

	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

}
