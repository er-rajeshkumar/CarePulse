package com.carepulse.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carepulse.entity.HospitalDoctor;
import com.carepulse.entity.Status;

public interface HospitalDoctorRepository extends JpaRepository<HospitalDoctor, Long>{

	 @Override
	 List<HospitalDoctor> findAll();

	    List<HospitalDoctor> findAllByStatus(Status status);

	    List<HospitalDoctor> findAllByHospital_HospitalId(Long hospitalId);

	    List<HospitalDoctor> findAllByHospital_HospitalIdAndStatus(Long hospitalId, Status status);

	    List<HospitalDoctor> findAllByDoctor_DoctorId(Long doctorId);

	    List<HospitalDoctor> findAllByDoctor_DoctorIdAndStatus(Long doctorId, Status status);

	    Optional<HospitalDoctor> findByHospital_HospitalIdAndDoctor_DoctorId(Long hospitalId, Long doctorId);

	    boolean existsByHospital_HospitalIdAndDoctor_DoctorId(Long hospitalId, Long doctorId);
}
