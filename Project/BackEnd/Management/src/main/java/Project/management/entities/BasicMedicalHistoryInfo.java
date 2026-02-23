package Project.management.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "basic_medical_history_info")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BasicMedicalHistoryInfo extends MedicalHistoryInfo {

    @Column(nullable = false, length = 50)
    private String label;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private MedicalHistoryInfoType type;

}
