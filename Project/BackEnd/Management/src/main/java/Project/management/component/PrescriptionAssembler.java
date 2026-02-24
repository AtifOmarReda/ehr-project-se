package Project.management.component;

import Project.management.dto.ConsultationItemDTO;
import Project.management.entities.Consultation;
import Project.management.entities.ConsultationItem;
import Project.management.entities.Prescription;

public class PrescriptionAssembler implements ConsultationItemAssembler {

    @Override
    public ConsultationItem create(ConsultationItemDTO dto, Consultation consultation) {

        Prescription prescription = Prescription.builder()
                .date(dto.getDate())
                .build();
        prescription.setConsultation(consultation);

        return prescription;

    }

    @Override
    public void update(ConsultationItem existing, ConsultationItemDTO dto) {

        Prescription prescriptionExisting = (Prescription) existing;
        prescriptionExisting.setDate(dto.getDate());

    }
}
