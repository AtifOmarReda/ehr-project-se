package Project.management.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "medical_history_info")
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class MedicalHistoryInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;
}
