package Project.management.components.strategies;

import Project.management.dto.ConsultationItemChildDTO;
import Project.management.entities.*;

public class LabExamItemStrategy implements ConsultationItemChildStrategy {
    @Override
    public boolean supports(ConsultationItem item) {
        return item instanceof LabExam;
    }

    @Override
    public void addChild(ConsultationItem item, ConsultationItemChildDTO dto) {
        LabExam labExam = (LabExam) item;

        LabExamItem labExamItem = LabExamItem.builder()
                .label(dto.getLabel())
                .build();

        labExamItem.setLab_exam(labExam);
        labExam.getLabExamItems().add(labExamItem);
    }
}
