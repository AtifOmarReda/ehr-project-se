package Project.management.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Table(name = "medical_history_info")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class MedicalHistoryInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private MedicalHistory medicalHistory;
}
