package Project.management.entities;

import jakarta.persistence.*;
import lombok.*;

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

    @Column(nullable = false)
    private List<Double> parameters;

    @Column(nullable = false, length = 50)
    private String note;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

}
