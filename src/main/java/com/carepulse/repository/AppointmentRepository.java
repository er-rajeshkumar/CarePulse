package com.carepulse.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carepulse.entity.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

	List<Appointment> findAllByDoctor_DoctorId(Long doctorId);
	List<Appointment> findAllByPatient_PatientId(Long patientId);
	List<Appointment> findAllByHospital_HospitalId(Long hospitalId);
	List<Appointment> findAllByPatientCase_Id(Long patientCaseId);
}
