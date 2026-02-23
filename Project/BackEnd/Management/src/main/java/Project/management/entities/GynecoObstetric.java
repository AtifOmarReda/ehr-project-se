package Project.management.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "gyneco_obstetric")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GynecoObstetric extends MedicalHistoryInfo {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MedicalHistoryInfoType type;

    @Column(nullable = false)
    private Boolean dysmenorrhea;

    @Column(nullable = false)
    private Boolean dyspareunia;

    @Column(nullable = false)
    private Boolean endometriosis;

    @Column(nullable = false, length = 50)
    private String noteAnamoly;

    @Column(nullable = false, length = 50)
    private Boolean salpingitis;

    @Column(nullable = false)
    private Boolean urogenitalTuborculosis;

    @Column(nullable = false, length = 50)
    private String oralContraceptives;

    @Column(nullable = false, length = 50)
    private String iud;

    @Column(nullable = false, length = 50)
    private String noteContraception;

    @Column(nullable = false, length = 50)
    private String duration;

    @Column(nullable = false, length = 50)
    private String reportingFrequency;

    @Column(nullable = false, length = 50)
    private String cycleLength;

    @Column(nullable = false, length = 50)
    private String cycleNature;

    @Column(nullable = false, length = 50)
    private String firstRules;

    @Column(nullable = false, length = 50)
    private String abortion;

    @Column(nullable = false, length = 50)
    private String cSection;

    @Column(nullable = false, length = 50)
    private String pregnancyDesire;

    @Column(nullable = false, length = 50)
    private String geu;

    @Column(nullable = false, length = 50)
    private String previousPregnancies;

    @Column(nullable = false, length = 50)
    private Boolean bcg;
}
