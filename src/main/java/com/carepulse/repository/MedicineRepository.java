package com.carepulse.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carepulse.entity.Medicine;
import com.carepulse.entity.Status;

public interface MedicineRepository extends JpaRepository<Medicine, Long> {

	List<Medicine> findAllByStatus(Status status);
	Optional<Medicine> findByMedicineIdAndStatus(Long medicineId, Status status);
	boolean existsByMedicineId(Long medicineId);
	boolean existsByMedicineName(String medicineName);
	
	List<Medicine> findAllByBrandName(String brandName);
	List<Medicine> findAllByMedicineNameAndStatus(String medicineName, Status status);
}
