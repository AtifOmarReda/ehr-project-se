package Project.management.services;

import Project.management.components.contexts.ConsultationItemChildContext;
import Project.management.dto.ConsultationItemChildDTO;
import Project.management.entities.*;
import Project.management.repositories.ConsultationItemRepository;
import Project.management.repositories.MedicalHistoryInfoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ConsultationItemServiceTests {

    @Mock
    private ConsultationItemRepository consultationItemRepository;

    @Spy
    private ConsultationItemChildContext consultationItemChildContext;

    @InjectMocks
    private ConsultationItemService consultationItemService;

    private ConsultationItemChildDTO dto;

    @BeforeEach
    void setUp() {
        dto = ConsultationItemChildDTO.builder()
                .label("Paracetamol 1000mg")
                .dailyIntake(3L)
                .build();
    }

    // Testing only addChild method
    // All other methods share the same logic as MedicalHistoryInfoService and PatientService
    // therefore they are not tested
    @Test
    public void testAddChildSuccess() {

        Prescription testPrescription = new Prescription();
        Medication testMedication = new Medication();

        testPrescription.setDate(LocalDate.parse("2026-02-26"));
        testPrescription.setType(ConsultationItemType.PRESCRIPTION);
        testPrescription.setMedications(new ArrayList<>());

        testMedication.setLabel("Paracetamol 1000mg");
        testMedication.setDailyIntake(3L);

        when(consultationItemRepository.findById(testPrescription.getId())).thenReturn(Optional.of(testPrescription));
        when(consultationItemRepository.save(any(ConsultationItem.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Prescription prescription = (Prescription) consultationItemService.addChild(testPrescription.getId(), dto);

        assertNotNull(prescription);
        Medication medication = prescription.getMedications().getFirst();

        assertEquals(testMedication.getLabel(), medication.getLabel());
        assertEquals(testMedication.getDailyIntake(), medication.getDailyIntake());

    }

    @Test
    public void testAddChildException() {

        RuntimeException exception = assertThrows(RuntimeException.class, () -> consultationItemService.addChild(1L, dto));
        assertEquals("Consultation item doesn't exist!", exception.getMessage());

    }

}