package Project.management.dto;

import Project.management.entities.MedicalHistoryInfoType;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MedicalHistoryInfoDTO {

    @Size(max = 50)
    private String label;

    @Size(max = 50)
    private MedicalHistoryInfoType type;

    private Boolean dysmenorrhea;

    private Boolean dyspareunia;

    private Boolean endometriosis;

    @Size(max = 50)
    private String noteAnamoly;

    private Boolean salpingitis;

    private Boolean urogenitalTuborculosis;

    @Size(max = 50)
    private String oralContraceptives;

    @Size(max = 50)
    private String IUD;

    @Size(max = 50)
    private String noteContraception;

    @Size(max = 50)
    private String duration;

    @Size(max = 50)
    private String reportingFrequency;

    @Size(max = 50)
    private String cycleLength;

    @Size(max = 50)
    private String cycleNature;

    @Size(max = 50)
    private String firstRules;

    @Size(max = 50)
    private String abortion;

    @Size(max = 50)
    private String c_section;

    @Size(max = 50)
    private String pregnancyDesire;

    @Size(max = 50)
    private String GEU;

    @Size(max = 50)
    private String previousPregnancies;

    private Boolean BCG;

}
