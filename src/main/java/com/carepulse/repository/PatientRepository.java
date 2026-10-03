package com.carepulse.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.carepulse.entity.Patient;
import com.carepulse.enums.Status;

public interface PatientRepository extends JpaRepository<Patient, Long> {

	List<Patient> findAllByStatus(Status status);

	Optional<Patient> findByPatientIdAndStatus(Long patientId, Status status);

	boolean existsByPatientIdAndStatus(Long patientId, Status status);

	boolean existsByEmail(String email);

	boolean existsByPhone(String phoneNumber);

	@Override
	boolean existsById(Long id);

	@Query("""
		       SELECT p.status, COUNT(p)
		       FROM Patient p
		       GROUP BY p.status
		       """)
	List<Object[]> getPatientCountByStatus();
}