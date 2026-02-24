package Project.management.component;

import Project.management.dto.ConsultationItemDTO;
import Project.management.entities.LabExam;
import Project.management.entities.ConsultationItem;
import Project.management.entities.Consultation;

public class LabExamAssembler implements ConsultationItemAssembler {

    @Override
    public ConsultationItem create(ConsultationItemDTO dto, Consultation consultation) {

        LabExam labExam = LabExam.builder()
                .date(dto.getDate())
                .build();
        labExam.setConsultation(consultation);

        return labExam;

    }

    @Override
    public void update(ConsultationItem existing, ConsultationItemDTO dto) {

        LabExam labExamExisting = (LabExam) existing;
        labExamExisting.setDate(dto.getDate());

    }
}
