package Project.management.components.assemblers;

import Project.management.dto.ConsultationItemDTO;
import Project.management.entities.ConsultationItem;
import Project.management.entities.Consultation;
import Project.management.entities.Document;

public class DocumentAssembler implements ConsultationItemAssembler {

    @Override
    public ConsultationItem create(ConsultationItemDTO dto, Consultation consultation) {

        Document document = Document.builder()
                .documentUrl(dto.getDocumentUrl())
                .documentName(dto.getDocumentName())
                .type(dto.getType())
                .build();
        document.setConsultation(consultation);

        return document;

    }

    @Override
    public void update(ConsultationItem existing, ConsultationItemDTO dto) {

        Document documentExisting = (Document) existing;
        documentExisting.setDocumentUrl(dto.getDocumentUrl());
        documentExisting.setDocumentName(dto.getDocumentName());

    }
}
