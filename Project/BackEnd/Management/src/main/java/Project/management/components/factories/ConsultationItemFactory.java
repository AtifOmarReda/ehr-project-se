package Project.management.components.factories;

import Project.management.components.assemblers.*;
import Project.management.dto.ConsultationItemDTO;
import Project.management.entities.ConsultationItem;
import Project.management.entities.Consultation;
import Project.management.entities.ConsultationItemType;
import org.springframework.stereotype.Component;


/*
*   Used factory to create multiple types of similar entities
*   Using Enum of type ConsultationItemType in a switch to choose specific assembler capable of creating and updating entities
*   No need to throw an Exception because Spring Boot by default doesn't allow non existing Enum values
*/

@Component
public class ConsultationItemFactory {

    CourierAssembler courierCreator = new CourierAssembler();
    ReportAssembler reportCreator = new ReportAssembler();
    PrescriptionAssembler prescriptionCreator = new PrescriptionAssembler();
    DocumentAssembler documentCreator = new DocumentAssembler();
    LabExamAssembler labExamCreator = new LabExamAssembler();

    public ConsultationItem create(ConsultationItemDTO dto, Consultation consultation) {

        ConsultationItemType type = dto.getType();

        return switch (type) {
            case ConsultationItemType.COURIER -> courierCreator.create(dto, consultation);
            case ConsultationItemType.REPORT -> reportCreator.create(dto, consultation);
            case ConsultationItemType.PRESCRIPTION -> prescriptionCreator.create(dto, consultation);
            case ConsultationItemType.DOCUMENT -> documentCreator.create(dto, consultation);
            case ConsultationItemType.LAB_EXAM -> labExamCreator.create(dto, consultation);
        };

    }

    public void update(ConsultationItem existing, ConsultationItemDTO dto) {

        ConsultationItemType type = dto.getType();

         switch (type) {
            case ConsultationItemType.COURIER -> courierCreator.update(existing, dto);
            case ConsultationItemType.REPORT -> reportCreator.update(existing, dto);
            case ConsultationItemType.PRESCRIPTION -> prescriptionCreator.update(existing, dto);
            case ConsultationItemType.DOCUMENT -> documentCreator.update(existing, dto);
            case ConsultationItemType.LAB_EXAM -> labExamCreator.update(existing, dto);
        };
    }
}