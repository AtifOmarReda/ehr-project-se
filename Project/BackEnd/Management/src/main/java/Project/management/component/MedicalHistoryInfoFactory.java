package Project.management.component;

import Project.management.dto.MedicalHistoryInfoDTO;
import Project.management.entities.MedicalHistoryInfo;
import Project.management.entities.MedicalHistoryInfoType;
import org.springframework.stereotype.Component;

@Component
public class MedicalHistoryInfoFactory {

    BasicMedicalHistoryInfoCreator basicMedicalHistoryInfoCreator = new BasicMedicalHistoryInfoCreator();
    GynecoObstetricCreator gynecoObstetricCreator = new GynecoObstetricCreator();

    public MedicalHistoryInfo create(MedicalHistoryInfoDTO dto) {
        if (dto.getType() == MedicalHistoryInfoType.GYNECO_OBSETETRIC)
            return gynecoObstetricCreator.create(dto);
        else
            return basicMedicalHistoryInfoCreator.create(dto);
    }

    public void update(MedicalHistoryInfo existing, MedicalHistoryInfoDTO dto) {
        if (dto.getType() == MedicalHistoryInfoType.GYNECO_OBSETETRIC)
            gynecoObstetricCreator.update(existing,dto);
        else
            basicMedicalHistoryInfoCreator.update(existing,dto);
    }
}