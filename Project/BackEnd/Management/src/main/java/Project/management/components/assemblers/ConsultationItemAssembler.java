package Project.management.components.assemblers;

import Project.management.dto.ConsultationItemDTO;
import Project.management.entities.Consultation;
import Project.management.entities.ConsultationItem;
import org.springframework.stereotype.Component;

@Component
public interface ConsultationItemAssembler {
    ConsultationItem create(ConsultationItemDTO dto, Consultation consultation);
    void update (ConsultationItem existing, ConsultationItemDTO dto);
}