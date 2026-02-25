package Project.management.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConsultationDTO {

    @Size(max = 50)
    private String reasonForVisit;

    @Size(max = 50)
    private String clinicalExam;

    @Size(max = 50)
    private String paraclinicalExam;

    @Size(max = 50)
    private String diagnosis;

    @Size(max = 50)
    private String conductToFollow;

    @Size(max = 50)
    private String requestedExams;

    @Size(max = 50)
    private String illnessHistory;

    private List<Double> parameters;

    @Size(max = 50)
    private String note;

    private Long patientId;

}
