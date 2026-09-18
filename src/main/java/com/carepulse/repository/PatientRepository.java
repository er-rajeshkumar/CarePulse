package com.carepulse.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carepulse.entity.Patient;
import com.carepulse.entity.Status;

public interface PatientRepository extends JpaRepository<Patient, Long> {

	List<Patient> findAllByStatus(Status status);
	Optional<Patient> findByPatientIdAndStatus(
			Long patientId,
			Status status
			);
}