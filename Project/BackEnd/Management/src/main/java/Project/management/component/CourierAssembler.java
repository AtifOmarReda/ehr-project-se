package Project.management.component;

import Project.management.dto.ConsultationItemDTO;
import Project.management.entities.ConsultationItem;
import Project.management.entities.Courier;
import Project.management.entities.Consultation;

public class CourierAssembler implements ConsultationItemAssembler {

    @Override
    public ConsultationItem create(ConsultationItemDTO dto, Consultation consultation) {

        Courier courier = Courier.builder()
                .documentUrl(dto.getDocumentUrl())
                .build();
        courier.setConsultation(consultation);

        return courier;

    }

    @Override
    public void update(ConsultationItem existing, ConsultationItemDTO dto) {

        Courier courierExisting = (Courier) existing;
        courierExisting.setDocumentUrl(dto.getDocumentUrl());

    }
}
