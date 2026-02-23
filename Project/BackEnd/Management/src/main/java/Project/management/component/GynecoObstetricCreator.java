package Project.management.component;

import Project.management.dto.MedicalHistoryInfoDTO;
import Project.management.entities.GynecoObstetric;
import Project.management.entities.MedicalHistoryInfo;
import org.springframework.stereotype.Component;

@Component
public class GynecoObstetricCreator implements MedicalHistoryInfoCreator{

    @Override
    public MedicalHistoryInfo create(MedicalHistoryInfoDTO dto) {
        System.out.println(dto);
        return GynecoObstetric.builder()
                     .dysmenorrhea(dto.getDysmenorrhea())
                     .dyspareunia(dto.getDyspareunia())
                     .endometriosis(dto.getEndometriosis())
                     .noteAnamoly(dto.getNoteAnamoly())
                     .salpingitis(dto.getSalpingitis())
                     .urogenitalTuborculosis(dto.getUrogenitalTuborculosis())
                     .oralContraceptives(dto.getOralContraceptives())
                     .iud(dto.getIud())
                     .noteContraception(dto.getNoteContraception())
                     .duration(dto.getDuration())
                     .reportingFrequency(dto.getReportingFrequency())
                     .cycleLength(dto.getCycleLength())
                     .cycleNature(dto.getCycleNature())
                     .firstRules(dto.getFirstRules())
                     .abortion(dto.getAbortion())
                     .cSection(dto.getCSection())
                     .pregnancyDesire(dto.getPregnancyDesire())
                     .geu(dto.getGeu())
                     .previousPregnancies(dto.getPreviousPregnancies())
                     .bcg(dto.getBcg())
                     .type(dto.getType())
                     .build();
    }

    @Override
    public void update(MedicalHistoryInfo existing, MedicalHistoryInfoDTO dto) {
        GynecoObstetric gynecoObstetricExisting = (GynecoObstetric) existing;
        gynecoObstetricExisting.setDysmenorrhea(dto.getDysmenorrhea());
        gynecoObstetricExisting.setDyspareunia(dto.getDyspareunia());
        gynecoObstetricExisting.setEndometriosis(dto.getEndometriosis());
        gynecoObstetricExisting.setNoteAnamoly(dto.getNoteAnamoly());
        gynecoObstetricExisting.setSalpingitis(dto.getSalpingitis());
        gynecoObstetricExisting.setUrogenitalTuborculosis(dto.getUrogenitalTuborculosis());
        gynecoObstetricExisting.setOralContraceptives(dto.getOralContraceptives());
        gynecoObstetricExisting.setIud(dto.getIud());
        gynecoObstetricExisting.setNoteContraception(dto.getNoteContraception());
        gynecoObstetricExisting.setDuration(dto.getDuration());
        gynecoObstetricExisting.setReportingFrequency(dto.getReportingFrequency());
        gynecoObstetricExisting.setCycleLength(dto.getCycleLength());
        gynecoObstetricExisting.setCycleNature(dto.getCycleNature());
        gynecoObstetricExisting.setFirstRules(dto.getFirstRules());
        gynecoObstetricExisting.setAbortion(dto.getFirstRules());
        gynecoObstetricExisting.setCSection(dto.getCSection());
        gynecoObstetricExisting.setPregnancyDesire(dto.getPregnancyDesire());
        gynecoObstetricExisting.setGeu(dto.getGeu());
        gynecoObstetricExisting.setPreviousPregnancies(dto.getPreviousPregnancies());
        gynecoObstetricExisting.setBcg(dto.getBcg());
    }

}