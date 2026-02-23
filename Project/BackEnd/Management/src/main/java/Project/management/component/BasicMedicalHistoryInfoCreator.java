package Project.management.component;

import Project.management.dto.MedicalHistoryInfoDTO;
import Project.management.entities.BasicMedicalHistoryInfo;
import Project.management.entities.GynecoObstetric;
import Project.management.entities.MedicalHistoryInfo;
import org.springframework.stereotype.Component;

@Component
public class BasicMedicalHistoryInfoCreator implements MedicalHistoryInfoCreator{
    @Override
    public MedicalHistoryInfo create(MedicalHistoryInfoDTO dto) {
        return BasicMedicalHistoryInfo.builder()
                                    .label(dto.getLabel())
                                    .type(dto.getType())
                                    .build();
    }

    @Override
    public void update(MedicalHistoryInfo existing, MedicalHistoryInfoDTO dto) {
        BasicMedicalHistoryInfo basicExisting = (BasicMedicalHistoryInfo) existing;
        basicExisting.setLabel(dto.getLabel());
    }
}