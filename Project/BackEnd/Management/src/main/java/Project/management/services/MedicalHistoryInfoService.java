package Project.management.services;

import Project.management.components.MedicalHistoryInfoFactory;
import Project.management.dto.MedicalHistoryInfoDTO;
import Project.management.entities.MedicalHistoryInfo;
import Project.management.entities.Patient;
import Project.management.repositories.MedicalHistoryInfoRepository;
import Project.management.repositories.PatientRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MedicalHistoryInfoService {
    private final MedicalHistoryInfoRepository medicalHistoryInfoRepository;
    private final PatientRepository patientRepository;
    private final MedicalHistoryInfoFactory medicalHistoryInfoFactory;

    public MedicalHistoryInfo saveMedicalHistoryInfo(MedicalHistoryInfoDTO dto) {
        Patient patient = patientRepository.findById(dto.getPatientId())
                .orElseThrow(() -> new RuntimeException("Patient does not exist"));
        return medicalHistoryInfoRepository.save(medicalHistoryInfoFactory.create(dto, patient));
    }

    public List<MedicalHistoryInfo> getAllMedicalHistoryInfos() {
        return medicalHistoryInfoRepository.findAll();
    }

    public Optional<MedicalHistoryInfo> getMedicalHistoryInfoById(Long id) {
        return medicalHistoryInfoRepository.findById(id);
    }

    @Transactional
    public MedicalHistoryInfo update(Long id, MedicalHistoryInfoDTO dto) {
        MedicalHistoryInfo existing = medicalHistoryInfoRepository.findById(id).orElseThrow(() -> new RuntimeException("Medical history info doesn't exist!"));
        medicalHistoryInfoFactory.update(existing, dto);
        return medicalHistoryInfoRepository.save(existing);
    }

    public void deleteMedicalHistoryInfo(Long id) {
        if (!medicalHistoryInfoRepository.existsById(id)) {
            throw new RuntimeException("Medical history info doesn't exist, therefore it could not be deleted!");
        }
        medicalHistoryInfoRepository.deleteById(id);
    }

}