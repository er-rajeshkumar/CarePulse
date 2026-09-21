package com.carepulse.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carepulse.entity.HospitalPatient;

public interface HospitalPatientRepository extends JpaRepository<HospitalPatient, Long> {
    List<HospitalPatient> findAll();
    List<HospitalPatient> findAllByHospital_HospitalId(Long hospitalId);
    List<HospitalPatient> findAllByPatient_PatientId(Long patientId);
    Optional<HospitalPatient> findByHospital_HospitalIdAndPatient_PatientId(Long hospitalId, Long patientId);
    boolean existsByHospital_HospitalIdAndPatient_PatientId(Long hospitalId, Long patientId);
    boolean existsByHospital_HospitalIdAndHospitalPatientNo(Long hospitalId, String hospitalPatientNo);
}
