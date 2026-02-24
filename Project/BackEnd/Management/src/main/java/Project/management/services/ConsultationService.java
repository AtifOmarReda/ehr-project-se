package Project.management.services;

import Project.management.dto.ConsultationDTO;
import Project.management.entities.Consultation;
import Project.management.entities.Patient;
import Project.management.repositories.ConsultationRepository;
import Project.management.repositories.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ConsultationService {

    private final ConsultationRepository consultationRepository;
    private final PatientRepository patientRepository;

    private Consultation mapToEntity(ConsultationDTO dto, Patient patient) {
        Consultation consultation = Consultation.builder()
                .reasonForVisit(dto.getReasonForVisit())
                .clinicalExam(dto.getClinicalExam())
                .paraclinicalExam(dto.getParaclinicalExam())
                .diagnosis(dto.getDiagnosis())
                .conductToFollow(dto.getConductToFollow())
                .requestedExams(dto.getRequestedExams())
                .illnessHistory(dto.getIllnessHistory())
                .parameters(dto.getParameters())
                .note(dto.getNote())
                .build();

        consultation.setPatient(patient);

        return consultation;
    }

    public Consultation saveConsultation(ConsultationDTO dto) {

        Patient patient = patientRepository.findById(dto.getPatientId())
                .orElseThrow(() -> new RuntimeException("Patient does not exist"));

        Consultation consultation = mapToEntity(dto, patient);
        return consultationRepository.save(consultation);

    }

    public List<Consultation> getAllConsultations() {
        return consultationRepository.findAll();
    }

    public Optional<Consultation> getConsultationById(Long id) {
        return consultationRepository.findById(id);
    }

    @Transactional
    public Consultation updateConsultation(Long id, ConsultationDTO dto) {
        return consultationRepository.findById(id).map(existing -> {
            existing.setNote(dto.getNote());
            existing.setDiagnosis(dto.getDiagnosis());
            existing.setClinicalExam(dto.getClinicalExam());
            existing.setParameters(dto.getParameters());
            existing.setIllnessHistory(dto.getIllnessHistory());
            existing.setConductToFollow(dto.getConductToFollow());
            existing.setRequestedExams(dto.getRequestedExams());
            existing.setParaclinicalExam(dto.getParaclinicalExam());
            existing.setReasonForVisit(dto.getReasonForVisit());
            return consultationRepository.save(existing);
        }).orElseThrow(() -> new RuntimeException("Consultation not found"));
    }

    public void deleteConsultation(Long id) {
        if (!consultationRepository.existsById(id)) {
            throw new RuntimeException("Unable to delete: consultation not found");
        }
        consultationRepository.deleteById(id);
    }

}
