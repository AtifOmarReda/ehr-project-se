package Project.management.dto;

import Project.management.entities.MedicalHistoryInfoType;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    private String iud;

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

    @JsonProperty("cSection")
    @Size(max = 50)
    private String cSection;

    @Size(max = 50)
    private String pregnancyDesire;

    @Size(max = 50)
    private String geu;

    @Size(max = 50)
    private String previousPregnancies;

    private Boolean bcg;

    private long patientId;

}
