package com.carepulse.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carepulse.entity.Specialization;
import com.carepulse.entity.Status;

public interface SpecializationRepository extends JpaRepository<Specialization, Long>  {

	List<Specialization> findAllByStatus(Status status);
	Optional<Specialization> findBySpecializationIdAndStatus(Long id, Status status);
	Optional<Specialization> findBySpecializationCodeAndSpecializationIdNot(
	        String specializationCode,
	        Long specializationId
	);
	boolean existsBySpecializationCode(String specializationCode);
}
