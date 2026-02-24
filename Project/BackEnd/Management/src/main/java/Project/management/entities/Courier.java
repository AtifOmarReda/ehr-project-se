package Project.management.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Entity
@Table(name = "courier")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Courier extends ConsultationItem {

    @Column(nullable = false, length = 50)
    private String documentUrl;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private ConsultationItemType type;

}
