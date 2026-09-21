package com.carepulse.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carepulse.entity.PatientCase;

public interface PatientCaseRepository extends JpaRepository<PatientCase, Long> {

	List<PatientCase> findByPatient_PatientId(Long patientId);
	List<PatientCase> findByDoctor_DoctorId(Long patientId);
	List<PatientCase> findByHospital_HospitalId(Long patientId);
	
	
}
