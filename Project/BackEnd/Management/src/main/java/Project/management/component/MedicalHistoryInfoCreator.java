package Project.management.component;

import Project.management.dto.MedicalHistoryInfoDTO;
import Project.management.entities.MedicalHistoryInfo;
import org.springframework.stereotype.Component;

@Component
public interface MedicalHistoryInfoCreator {
    MedicalHistoryInfo create(MedicalHistoryInfoDTO dto);
    void update (MedicalHistoryInfo existing, MedicalHistoryInfoDTO dto);
}