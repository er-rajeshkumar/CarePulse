package com.carepulse.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carepulse.entity.HospitalDoctor;
import com.carepulse.entity.Status;

public interface HospitalDoctorRepository extends JpaRepository<HospitalDoctor, Long>{

	List<HospitalDoctor> findAll();
	List<HospitalDoctor> findAllByStatus(Status status);
	List<HospitalDoctor> findAllByHospitalId(Long hospitalId);
	List<HospitalDoctor> findAllByHospitalIdAndStatus(Long hospitalId, Status status);
	
	List<HospitalDoctor> findAllByDoctorId(Long doctorId);
	List<HospitalDoctor> findAllByDoctorIdAndStatus(Long doctorId, Status status);
	
	Optional<HospitalDoctor> findByHospitalIdAndDoctorId(Long hospitalId, Long doctorId);
	
	boolean existsByHospitalIdAndDoctorId(Long hospitalId, Long doctorId);
}
