package Project.management.repositories;

import Project.management.entities.MedicalHistoryInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalHistoryInfoRepository extends JpaRepository<MedicalHistoryInfo, Long> {
}
