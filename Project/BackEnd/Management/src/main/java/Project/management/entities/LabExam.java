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
@Table(name = "lab_exam")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LabExam extends ConsultationItem{

    @Column(nullable = false, length = 50)
    private LocalDate date;

    @OneToMany(mappedBy = "lab_exam")
    private List<LabExamItem> labExamItems;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private ConsultationItemType type;

}
