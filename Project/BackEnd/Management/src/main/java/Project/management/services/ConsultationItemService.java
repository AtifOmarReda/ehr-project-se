package Project.management.services;

import Project.management.component.ConsultationItemFactory;
import Project.management.dto.ConsultationItemDTO;
import Project.management.entities.Consultation;
import Project.management.entities.ConsultationItem;
import Project.management.repositories.ConsultationItemRepository;
import Project.management.repositories.ConsultationRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ConsultationItemService {
    private final ConsultationItemRepository consultationItemRepository;
    private final ConsultationRepository consultationRepository;
    private final ConsultationItemFactory consultationItemFactory;

    public ConsultationItem saveConsultationItem(ConsultationItemDTO dto) {
        Consultation consultation = consultationRepository.findById(dto.getConsultationId())
                .orElseThrow(() -> new RuntimeException("Consultation does not exist"));
        return consultationItemRepository.save(consultationItemFactory.create(dto, consultation));
    }

    public List<ConsultationItem> getConsultationItems() {
        return consultationItemRepository.findAll();
    }

    public Optional<ConsultationItem> getMedicalHistoryInfoById(Long id) {
        return consultationItemRepository.findById(id);
    }

    @Transactional
    public ConsultationItem update(Long id, ConsultationItemDTO dto) {
        ConsultationItem existing = consultationItemRepository.findById(id).orElseThrow(() -> new RuntimeException("Consultation item doesn't exist!"));
        consultationItemFactory.update(existing, dto);
        return consultationItemRepository.save(existing);
    }

    public void deleteMedicalHistoryInfo(Long id) {
        if (!consultationItemRepository.existsById(id)) {
            throw new RuntimeException("Consultation item doesn't exist, therefore it could not be deleted!");
        }
        consultationItemRepository.deleteById(id);
    }

}