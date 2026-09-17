package com.carepulse.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carepulse.entity.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {

}