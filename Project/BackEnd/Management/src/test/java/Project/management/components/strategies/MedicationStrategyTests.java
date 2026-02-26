package Project.management.components.strategies;

import Project.management.dto.ConsultationItemChildDTO;
import Project.management.entities.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/*
 *   Strategies share the same logic
 *   Testing one strategy is the equivalent of testing all
 */

@DataJpaTest
public class MedicationStrategyTests {

    final private MedicationStrategy strategy = new MedicationStrategy();

    final private Prescription prescription = new Prescription();
    final private Medication medication = new Medication();
    private ConsultationItemChildDTO dto = new ConsultationItemChildDTO();

    @BeforeEach
    void setUp() {
        prescription.setType(ConsultationItemType.PRESCRIPTION);
        prescription.setDate(LocalDate.parse("2026-02-25"));
        prescription.setMedications(new ArrayList<>());

        medication.setLabel("Paracetamol 1000g");
        medication.setDailyIntake(3L);

        medication.setPrescription(prescription);
        prescription.getMedications().add(medication);

        dto = ConsultationItemChildDTO.builder()
                .label("Paracetamol 1000g")
                .dailyIntake(3L)
                .build();
    }

    @Test
    public void testSupports() {
        assertTrue(strategy.supports(prescription));
    }

    @Test
    public void testAddChild() {

        Prescription testPrescription = new Prescription();
        testPrescription.setType(ConsultationItemType.PRESCRIPTION);
        testPrescription.setDate(LocalDate.parse("2026-02-25"));
        testPrescription.setMedications(new ArrayList<>());

        strategy.addChild(testPrescription, dto);

        Medication testMedication = testPrescription.getMedications().getFirst();

        assertNotNull(testMedication);
        assertEquals(medication.getLabel(), testMedication.getLabel());
        assertEquals(medication.getDailyIntake(), testMedication.getDailyIntake());
    }

}
