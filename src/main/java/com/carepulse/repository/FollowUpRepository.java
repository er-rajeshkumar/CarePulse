package com.carepulse.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carepulse.entity.FollowUp;

public interface FollowUpRepository extends JpaRepository<FollowUp, Long> {

	boolean existsById(Long followUpId);
	
	List<FollowUp> findByPatientCaseId(Long patientCaseId);
	List<FollowUp> findByPatientCase_Doctor_DoctorId(Long doctorId);
	List<FollowUp> findByPatientCase_Patient_PatientId(Long patientId);
	List<FollowUp> findByPatientCase_Hospital_HospitalId(Long hospitalId);
}
