package Project.management.dto;

import Project.management.entities.ConsultationItemType;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConsultationItemChildDTO {

    @Size(max = 50)
    private String label;

    private Long dailyIntake;

}
