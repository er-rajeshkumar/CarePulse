package com.carepulse.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carepulse.entity.Medication;

public interface MedicationRepository extends JpaRepository<Medication, Long> {
	Optional<Medication> findByMedicationId(Long medicationId);
	List<Medication> findAllByPatientCase_Id(Long patientCaseId);
	List<Medication> findAllByDoctor_DoctorId(Long doctorId);

}