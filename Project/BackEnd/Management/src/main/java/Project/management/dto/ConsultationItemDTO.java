package Project.management.dto;

import Project.management.entities.ConsultationItemType;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConsultationItemDTO {

    private ConsultationItemType type;

    private LocalDate date;

    @Size(max = 50)
    private String documentUrl;

    @Size(max = 50)
    private String documentName;

    private long consultationId;

}
