package com.carepulse.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carepulse.entity.MedicineTake;
import com.carepulse.entity.MedicineTakeStatus;

public interface MedicineTakeRepository extends JpaRepository<MedicineTake, Long> {

	List<MedicineTake> findAllByReminder_ReminderId(Long reminderId);

	List<MedicineTake> findAllByMedication_PatientCase_Patient_PatientId(Long patientId);

	List<MedicineTake> findAllByMedication_Doctor_DoctorId(Long octorId);

	List<MedicineTake> findAllByStatus(MedicineTakeStatus statusEnum);
}
