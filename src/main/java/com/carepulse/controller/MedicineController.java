package com.carepulse.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.carepulse.dto.MedicineCreateRequestDto;
import com.carepulse.dto.MedicineResponseDto;
import com.carepulse.service.MedicineService;

import jakarta.validation.Valid;

@RestController
public class MedicineController {

	private final MedicineService medicineService;
	
	public MedicineController(MedicineService medicineService) {
		this.medicineService = medicineService;
	}
	
	@GetMapping("/carepulse/medicine/message")
	public String getMedicineMessage() {
		return medicineService.getMedicineMessage();
	}
	
	@GetMapping("/carepulse/medicine")
	public List<MedicineResponseDto> getAllMedicines() {
		return medicineService.getAllMedicines();
	}
	
	@GetMapping("/carepulse/medicine/active")
	public List<MedicineResponseDto> getAllActiveMedicines() {
		return medicineService.getAllActiveMedicines();
	}
	
	@GetMapping("/carepulse/medicine/{id}")
	public MedicineResponseDto getMedicineById(@PathVariable Long id) {
		return medicineService.getMedicineById(id);
	}
	@GetMapping("/carepulse/medicine/name/{name}")
	public List<MedicineResponseDto> getMedicineByName(@PathVariable String name) {
		return medicineService.getMedicineByName(name);
	}
	
	@GetMapping("/carepulse/medicine/brand/{brand}")
	public List<MedicineResponseDto> getMedicineByBrand(@PathVariable String brand) {
		return medicineService.getAllMedicinesByBrandName(brand);
	}
	
	@PostMapping("/carepulse/medicine")
	public MedicineResponseDto addMedicine(@Valid @RequestBody MedicineCreateRequestDto medicineCreateRequestDto) {
		return medicineService.addMedicine(medicineCreateRequestDto);
	}
	
	@PutMapping("/carepulse/medicine/{id}")
	public MedicineResponseDto updateMedicine(@PathVariable Long id, @Valid @RequestBody MedicineCreateRequestDto medicineCreateRequestDto) {
		return medicineService.updateMedicine(id, medicineCreateRequestDto);
	}
	
	@DeleteMapping("/carepulse/medicine/{id}")
	public MedicineResponseDto deleteMedicine(@PathVariable Long id) {
		return medicineService.deleteMedicine(id);
	}
	
}
