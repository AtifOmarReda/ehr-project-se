package Project.management.components.strategies;

import Project.management.dto.ConsultationItemChildDTO;
import Project.management.entities.ConsultationItem;
import Project.management.entities.Medication;
import Project.management.entities.Prescription;

public class MedicationStrategy implements ConsultationItemChildStrategy {
    @Override
    public boolean supports(ConsultationItem item) {
        return item instanceof Prescription;
    }

    @Override
    public void addChild(ConsultationItem item, ConsultationItemChildDTO dto) {

        Prescription prescription = (Prescription) item;

        Medication medication = Medication.builder()
                .dailyIntake(dto.getDailyIntake())
                .label(dto.getLabel())
                .build();

        medication.setPrescription(prescription);
        prescription.getMedications().add(medication);
    }
}
