package Project.management.components.assemblers;

import Project.management.dto.ConsultationItemDTO;
import Project.management.entities.ConsultationItem;
import Project.management.entities.Report;
import Project.management.entities.Consultation;

public class ReportAssembler implements ConsultationItemAssembler {

    @Override
    public ConsultationItem create(ConsultationItemDTO dto, Consultation consultation) {

        Report report = Report.builder()
                .documentUrl(dto.getDocumentUrl())
                .type(dto.getType())
                .build();
        report.setConsultation(consultation);

        return report;

    }

    @Override
    public void update(ConsultationItem existing, ConsultationItemDTO dto) {

        Report reportExisting = (Report) existing;
        reportExisting.setDocumentUrl(dto.getDocumentUrl());

    }
}
