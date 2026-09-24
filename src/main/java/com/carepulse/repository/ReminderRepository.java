package com.carepulse.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carepulse.entity.Reminder;

public interface ReminderRepository extends JpaRepository<Reminder, Long> {

	List<Reminder> findAllByMedication_PatientCase_Doctor_DoctorId(Long doctorId);
	List<Reminder> findAllByMedication_PatientCase_Patient_PatientId(Long patientId);
}
