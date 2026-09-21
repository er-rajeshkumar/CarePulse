package com.carepulse.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carepulse.entity.Hospital;
import com.carepulse.entity.Status;


public interface HospitalRepository extends JpaRepository<Hospital, Long>{

	Hospital findByHospitalId(Long id);
	List<Hospital> findAllByStatus(Status status);
	Optional<Hospital> findByHospitalIdAndStatus(Long id, Status status);
	boolean existsByHospitalId(Long hospitalId);
	boolean existsByHospitalEmail(String hospitalEmail);
	boolean existsByHospitalCode(String hospitalCode);
	boolean existsByHospitalCodeAndHospitalIdNot(String hospitalCode, Long hospitalId);
}
