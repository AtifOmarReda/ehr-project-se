package Project.management.components.strategies;

import Project.management.dto.ConsultationItemChildDTO;
import Project.management.entities.ConsultationItem;

public interface ConsultationItemChildStrategy {
    boolean supports(ConsultationItem item);
    void addChild(ConsultationItem item, ConsultationItemChildDTO dto);
}
