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
@Table(name = "document")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Document extends ConsultationItem {

    @Column(nullable = false, length = 50)
    private String documentUrl;

    @Column(nullable = false, length = 50)
    private String documentName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private ConsultationItemType type;

}
