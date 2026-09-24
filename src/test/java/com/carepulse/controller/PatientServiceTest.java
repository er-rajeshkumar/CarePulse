package com.carepulse.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.carepulse.dto.PatientResponseDto;
import com.carepulse.entity.Patient;
import com.carepulse.entity.Status;
import com.carepulse.repository.PatientRepository;
import com.carepulse.service.PatientService;

public class PatientServiceTest {

	PatientRepository repository = mock(PatientRepository.class);
	PatientService service = new PatientService(repository);

	@Test
	void testPatientServiceCreation() {

	}

	@Test
	void testGetAllPatientsByStatus() {

		Patient patient1 = new Patient();
		patient1.setPatientId(1L);
		patient1.setFirstName("Rajesh");
		patient1.setLastName("Kumar");
		patient1.setStatus(Status.ACTIVE);

		Patient patient2 = new Patient();
		patient2.setPatientId(2L);
		patient2.setFirstName("Juhi");
		patient2.setLastName("Singh");
		patient2.setStatus(Status.ACTIVE);

		List<Patient> patients = List.of(patient1, patient2);

		when(repository.findAllByStatus(Status.ACTIVE)).thenReturn(patients);

		List<PatientResponseDto> result = service.getAllPatientsByStatus();
		assertEquals(2, result.size());
		assert result.size() == 2;
		assertEquals("Rajesh Kumar", result.get(0).getFullName());
		assertEquals("Juhi Singh", result.get(1).getFullName());
		assertEquals(Status.ACTIVE, result.get(0).getStatus());
		assertEquals(Status.ACTIVE, result.get(1).getStatus());
		assertEquals(1L, result.get(0).getPatientId());
		assertEquals(2L, result.get(1).getPatientId());

		verify(repository).findAllByStatus(Status.ACTIVE);

	}
}
