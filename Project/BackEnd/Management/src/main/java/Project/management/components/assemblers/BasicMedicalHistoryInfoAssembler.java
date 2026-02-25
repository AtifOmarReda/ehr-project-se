package Project.management.components.assemblers;

import Project.management.dto.MedicalHistoryInfoDTO;
import Project.management.entities.BasicMedicalHistoryInfo;
import Project.management.entities.MedicalHistoryInfo;
import Project.management.entities.Patient;
import org.springframework.stereotype.Component;

@Component
public class BasicMedicalHistoryInfoAssembler implements MedicalHistoryInfoAssembler {
    @Override
    public MedicalHistoryInfo create(MedicalHistoryInfoDTO dto, Patient patient) {
        BasicMedicalHistoryInfo basicMedicalHistoryInfo = BasicMedicalHistoryInfo.builder()
                .label(dto.getLabel())
                .type(dto.getType())
                .build();
        basicMedicalHistoryInfo.setPatient(patient);
        return basicMedicalHistoryInfo;
    }

    @Override
    public void update(MedicalHistoryInfo existing, MedicalHistoryInfoDTO dto) {
        BasicMedicalHistoryInfo basicExisting = (BasicMedicalHistoryInfo) existing;
        basicExisting.setLabel(dto.getLabel());
    }
}