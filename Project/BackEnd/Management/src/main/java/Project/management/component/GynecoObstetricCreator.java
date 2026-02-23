package Project.management.component;

import Project.management.dto.MedicalHistoryInfoDTO;
import Project.management.entities.GynecoObstetric;
import Project.management.entities.MedicalHistoryInfo;
import org.springframework.stereotype.Component;

@Component
public class GynecoObstetricCreator implements MedicalHistoryInfoCreator{

    @Override
    public MedicalHistoryInfo create(MedicalHistoryInfoDTO dto) {
        return GynecoObstetric.builder()
                     .dysmenorrhea(dto.getDysmenorrhea())
                     .dyspareunia(dto.getDyspareunia())
                     .endometriosis(dto.getEndometriosis())
                     .noteAnamoly(dto.getNoteAnamoly())
                     .salpingitis(dto.getSalpingitis())
                     .urogenitalTuborculosis(dto.getUrogenitalTuborculosis())
                     .oralContraceptives(dto.getOralContraceptives())
                     .IUD(dto.getIUD())
                     .noteContraception(dto.getNoteContraception())
                     .duration(dto.getDuration())
                     .reportingFrequency(dto.getReportingFrequency())
                     .cycleLength(dto.getCycleLength())
                     .cycleNature(dto.getCycleNature())
                     .firstRules(dto.getFirstRules())
                     .abortion(dto.getFirstRules())
                     .c_section(dto.getC_section())
                     .pregnancyDesire(dto.getPregnancyDesire())
                     .GEU(dto.getGEU())
                     .previousPregnancies(dto.getPreviousPregnancies())
                     .BCG(dto.getBCG())
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
        gynecoObstetricExisting.setIUD(dto.getIUD());
        gynecoObstetricExisting.setNoteContraception(dto.getNoteContraception());
        gynecoObstetricExisting.setDuration(dto.getDuration());
        gynecoObstetricExisting.setReportingFrequency(dto.getReportingFrequency());
        gynecoObstetricExisting.setCycleLength(dto.getCycleLength());
        gynecoObstetricExisting.setCycleNature(dto.getCycleNature());
        gynecoObstetricExisting.setFirstRules(dto.getFirstRules());
        gynecoObstetricExisting.setAbortion(dto.getFirstRules());
        gynecoObstetricExisting.setC_section(dto.getC_section());
        gynecoObstetricExisting.setPregnancyDesire(dto.getPregnancyDesire());
        gynecoObstetricExisting.setGEU(dto.getGEU());
        gynecoObstetricExisting.setPreviousPregnancies(dto.getPreviousPregnancies());
        gynecoObstetricExisting.setBCG(dto.getBCG());
    }

}