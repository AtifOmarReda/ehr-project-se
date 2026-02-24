package Project.management.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.List;

@Entity
@Table(name = "consultations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Consultation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String reasonForVisit;

    @Column(nullable = false, length = 50)
    private String clinicalExam;

    @Column(nullable = false, length = 50)
    private String paraclinicalExam;

    @Column(nullable = false, length = 50)
    private String diagnosis;

    @Column(nullable = false, length = 50)
    private String conductToFollow;

    @Column(nullable = false, length = 50)
    private String requestedExams;

    @Column(nullable = false, length = 50)
    private String illnessHistory;

    // Currently includes: Weight, Height, Temperature, Diastolic Blood Pressure, Systolic Blood Pressure, Glycohemoglobin (A1C), Body Mass Index
    @Column(nullable = false)
    private List<Double> parameters;

    @Column(nullable = false, length = 50)
    private String note;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

}
