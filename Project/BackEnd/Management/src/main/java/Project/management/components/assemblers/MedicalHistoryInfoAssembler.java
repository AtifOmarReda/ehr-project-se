package Project.management.components.assemblers;

import Project.management.dto.MedicalHistoryInfoDTO;
import Project.management.entities.MedicalHistoryInfo;
import Project.management.entities.Patient;
import org.springframework.stereotype.Component;

@Component
public interface MedicalHistoryInfoAssembler {
    MedicalHistoryInfo create(MedicalHistoryInfoDTO dto, Patient patient);
    void update (MedicalHistoryInfo existing, MedicalHistoryInfoDTO dto);
}