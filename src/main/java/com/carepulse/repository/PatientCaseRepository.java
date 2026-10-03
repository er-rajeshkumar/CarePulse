package com.carepulse.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carepulse.entity.PatientCase;
import com.carepulse.enums.CaseStatus;

public interface PatientCaseRepository extends JpaRepository<PatientCase, Long> {

	List<PatientCase> findByPatient_PatientId(Long patientId);

	List<PatientCase> findByDoctor_DoctorId(Long patientId);

	List<PatientCase> findByHospital_HospitalId(Long patientId);

	long countByPatient_PatientIdAndCaseStatus(Long patientId, CaseStatus status);

	Optional<PatientCase> findFirstByPatient_PatientIdAndCaseStatus(
	        Long patientId,
	        CaseStatus caseStatus
	);

	long countDistinctByDoctor_DoctorIdAndCaseStatus(Long doctorId, CaseStatus caseStatus);
	long countByDoctor_DoctorIdAndCaseStatus(Long doctorId, CaseStatus caseStatus);
	long countDistinctByHospital_HospitalIdAndCaseStatus(Long hospitalId, CaseStatus caseStatus);
	long countDistinctByHospital_HospitalId(Long hospitalId);

	long countByHospital_HospitalIdAndCaseStatus(Long hospitalId, CaseStatus caseStatus);

	long countByCaseStatus(CaseStatus caseStatus);
}
