package com.carepulse.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.carepulse.dto.MedicineCreateRequestDto;
import com.carepulse.dto.MedicineResponseDto;
import com.carepulse.entity.Medicine;
import com.carepulse.entity.Status;
import com.carepulse.exception.MedicineException;
import com.carepulse.repository.MedicineRepository;

@Service
public class MedicineService {

	private final MedicineRepository medicineRepository;

	public MedicineService(MedicineRepository medicineRepository) {
		this.medicineRepository = medicineRepository;
	}

	private static final Logger logger = LoggerFactory.getLogger(MedicineService.class);

	public void testLog() {
		logger.trace("TRACE Log");
		logger.debug("DEBUG Log");
		logger.info("INFO Log");
		logger.warn("WARN Log");
		logger.error("ERROR Log");
	}

//	Method to get medicine service message
	public String getMedicineMessage() {
		return "Medicine Service is working!";
	}

//	Method to get all medicines from the database
	public List<MedicineResponseDto> getAllMedicines() {
		logger.info("Fetching all medicines from the database");
		List<Medicine> medicines = medicineRepository.findAll();
		List<MedicineResponseDto> medicineResponseDtos = new ArrayList<>();
		for (Medicine medicine : medicines) {
			MedicineResponseDto medicineResponseDto = convertToDto(medicine);
			medicineResponseDtos.add(medicineResponseDto);
		}
		logger.info("Fetched all medicines from the database");
		logger.debug("Total medicines found: {}", medicineResponseDtos.size());
		return medicineResponseDtos;
	}

//	Method to get all active medicines from the database
	public List<MedicineResponseDto> getAllActiveMedicines() {
		logger.info("Fetching all active medicines from the database");
		List<Medicine> medicines = medicineRepository.findAllByStatus(Status.ACTIVE);
		List<MedicineResponseDto> medicineResponseDtos = new ArrayList<>();
		for (Medicine medicine : medicines) {
			MedicineResponseDto medicineResponseDto = convertToDto(medicine);
			medicineResponseDtos.add(medicineResponseDto);
		}
		logger.info("Fetched all active medicines from the database");
		logger.debug("Total active medicines found: {}", medicineResponseDtos.size());
		return medicineResponseDtos;
	}

//	Method to get medicine by ID 
	public MedicineResponseDto getMedicineById(Long medicineId) {
		logger.info("Fetching medicine with ID: {} from the database", medicineId);
		boolean isExists = medicineRepository.existsById(medicineId);
		if (!isExists) {
			logger.error("Medicine not found with ID: {}", medicineId);
			throw new MedicineException("Medicine not found with ID: " + medicineId);
		}
		Medicine medicine = medicineRepository.findByMedicineIdAndStatus(medicineId, Status.ACTIVE)
				.orElseThrow(() -> new MedicineException("Medicine not found with ID: " + medicineId));
		logger.info("Fetched medicine with ID: {} from the database", medicineId);
		return convertToDto(medicine);
	}

//	Method to get medicine entity by ID
	public Medicine getMedicineEntityById(Long medicineId) {
		logger.info("Fetching medicine entity with ID: {} from the database", medicineId);
		boolean isExists = medicineRepository.existsById(medicineId);
		if (!isExists) {
			logger.error("Medicine not found with ID: {}", medicineId);
			throw new MedicineException("Medicine not found with ID: " + medicineId);
		}
		Medicine medicine = medicineRepository.findById(medicineId)
				.orElseThrow(() -> new MedicineException("Medicine not found with ID: " + medicineId));
		logger.info("Fetched medicine entity with ID: {} from the database", medicineId);
		return medicine;
	}

//	Method to check if medicine exists by ID
	public boolean isMedicineExistsById(Long medicineId) {
		logger.info("Checking if medicine exists with ID: {}", medicineId);
		boolean isExists = medicineRepository.existsById(medicineId);
		logger.info("Medicine exists with ID: {} - {}", medicineId, isExists);
		return isExists;
	}

//	Method to check if medicine exists by name
	public boolean isMedicineExistsByName(String medicineName) {
		logger.info("Checking if medicine exists with name: {}", medicineName);
		boolean isExists = medicineRepository.existsByMedicineName(medicineName);
		logger.info("Medicine exists with name: {} - {}", medicineName, isExists);
		return isExists;
	}

//	Method to get medicine by name
	public List<MedicineResponseDto> getMedicineByName(String medicineName) {
		logger.info("Fetching medicine with name: {} from the database", medicineName);
		List<Medicine> medicines = medicineRepository.findAllByMedicineNameAndStatus(medicineName, Status.ACTIVE);
		List<MedicineResponseDto> dtos = new ArrayList<>();
		for (Medicine medicine : medicines) {
			dtos.add(convertToDto(medicine));
		}
		logger.info("Fetched medicine with name: {} from the database", medicineName);
		return dtos;
	}

//	Method to get all medicine by brand name
	public List<MedicineResponseDto> getAllMedicinesByBrandName(String brandName) {
		logger.info("Fetching all medicines with brand name: {} from the database", brandName);
		List<Medicine> medicines = medicineRepository.findAllByBrandName(brandName);
		List<MedicineResponseDto> medicineResponseDtos = new ArrayList<>();
		for (Medicine medicine : medicines) {
			MedicineResponseDto medicineResponseDto = convertToDto(medicine);
			medicineResponseDtos.add(medicineResponseDto);
		}
		logger.info("Fetched all medicines with brand name: {} from the database", brandName);
		logger.debug("Total medicines found with brand name {}: {}", brandName, medicineResponseDtos.size());
		return medicineResponseDtos;
	}

//	Method to add medicine to the database
	public MedicineResponseDto addMedicine(MedicineCreateRequestDto medicineCreateRequestDto) {
		logger.info("Adding new medicine to the database");
		Medicine medicine = convertToEntity(medicineCreateRequestDto);
		medicine.setCreatedAt(Date.from(java.time.Instant.now()));
		Medicine savedMedicine = medicineRepository.save(medicine);
		logger.info("Added new medicine with ID: {} to the database", savedMedicine.getMedicineId());
		return convertToDto(savedMedicine);
	}

//	Method to update medicine to the database
	public MedicineResponseDto updateMedicine(Long id, MedicineCreateRequestDto medicineCreateRequestDto) {
		logger.info("Adding new medicine to the database");
		Medicine medicine = medicineRepository.findById(id)
				.orElseThrow(() -> new MedicineException("Medicine not found with ID: " + id));
		medicine.setMedicineName(medicineCreateRequestDto.getMedicineName());
		medicine.setBrandName(medicineCreateRequestDto.getBrandName());
		medicine.setForm(medicineCreateRequestDto.getForm());
		medicine.setStrength(medicineCreateRequestDto.getStrength());
		medicine.setDescription(medicineCreateRequestDto.getDescription());
		medicine.setUpdatedAt(Date.from(java.time.Instant.now()));
		Medicine savedMedicine = medicineRepository.save(medicine);
		logger.info("Updated medicine with ID: {} to the database", savedMedicine.getMedicineId());
		return convertToDto(savedMedicine);
	}

//	Method to soft delete medicine from the database
	public MedicineResponseDto deleteMedicine(Long id) {
		logger.info("Deleting medicine with ID: {} from the database", id);
		Medicine medicine = medicineRepository.findById(id)
				.orElseThrow(() -> new MedicineException("Medicine not found with ID: " + id));
		medicine.setStatus(Status.DELETED);
		medicine.setUpdatedAt(Date.from(java.time.Instant.now()));
		Medicine savedMedicine = medicineRepository.save(medicine);
		logger.info("Deleted medicine with ID: {} from the database", savedMedicine.getMedicineId());
		return convertToDto(savedMedicine);
	}

//	Helper method to convert Medicine entity to MedicineResponseDto
	private MedicineResponseDto convertToDto(Medicine medicine) {
		MedicineResponseDto dto = new MedicineResponseDto();
		dto.setMedicineId(medicine.getMedicineId());
		dto.setMedicineName(medicine.getMedicineName());
		dto.setBrandName(medicine.getBrandName());
		dto.setForm(medicine.getForm());
		dto.setStrength(medicine.getStrength());
		dto.setDescription(medicine.getDescription());
		dto.setStatus(medicine.getStatus());
		return dto;
	}

//	Helper method to convert MedicineCreateRequestDto to Medicine entity
	private Medicine convertToEntity(MedicineCreateRequestDto dto) {
		Medicine medicine = new Medicine();
		medicine.setMedicineName(dto.getMedicineName());
		medicine.setBrandName(dto.getBrandName());
		medicine.setForm(dto.getForm());
		medicine.setStrength(dto.getStrength());
		medicine.setDescription(dto.getDescription());
		medicine.setStatus(Status.ACTIVE);
		return medicine;
	}
}
