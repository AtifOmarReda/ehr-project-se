package Project.management.components.factories;

import Project.management.components.assemblers.BasicMedicalHistoryInfoAssembler;
import Project.management.components.assemblers.GynecoObstetricAssembler;
import Project.management.dto.MedicalHistoryInfoDTO;
import Project.management.entities.MedicalHistoryInfo;
import Project.management.entities.MedicalHistoryInfoType;
import Project.management.entities.Patient;
import org.springframework.stereotype.Component;


/*
*   Used factory to create multiple types of similar entities, specifically for more complex ones
*   Using Enum of type MedicalHistoryInfo to choose specific assembler capable of creating and updating entities
*   No need to throw an Exception because Spring Boot by default doesn't allow non existing Enum values
*/

@Component
public class MedicalHistoryInfoFactory {

    BasicMedicalHistoryInfoAssembler basicMedicalHistoryInfoAssembler = new BasicMedicalHistoryInfoAssembler();
    GynecoObstetricAssembler gynecoObstetricAssembler = new GynecoObstetricAssembler();

    public MedicalHistoryInfo create(MedicalHistoryInfoDTO dto, Patient patient) {
        if (dto.getType() == MedicalHistoryInfoType.GYNECO_OBSETETRIC) {
            return gynecoObstetricAssembler.create(dto, patient);
        }
        else {
            return basicMedicalHistoryInfoAssembler.create(dto, patient);
        }
    }

    public void update(MedicalHistoryInfo existing, MedicalHistoryInfoDTO dto) {
        if (dto.getType() == MedicalHistoryInfoType.GYNECO_OBSETETRIC)
            gynecoObstetricAssembler.update(existing,dto);
        else
            basicMedicalHistoryInfoAssembler.update(existing,dto);
    }
}