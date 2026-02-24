package Project.management.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "prescription")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Prescription extends ConsultationItem{

    @Column(nullable = false, length = 50)
    private LocalDate date;

    @OneToMany(mappedBy = "prescription")
    private List<Medication> medications;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private ConsultationItemType type;

}
