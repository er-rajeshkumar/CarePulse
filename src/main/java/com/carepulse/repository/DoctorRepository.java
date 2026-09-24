package com.carepulse.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carepulse.entity.Doctor;
import com.carepulse.entity.Status;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

	List<Doctor> findAllByStatus(Status status);

	Optional<Doctor> findByDoctorIdAndStatus(Long id, Status status);

	boolean existsByEmail(String email);

	boolean existsByDoctorRegistrationNo(String doctorRegistrationNo);

	boolean existsByDoctorId(Long doctorId);
}
