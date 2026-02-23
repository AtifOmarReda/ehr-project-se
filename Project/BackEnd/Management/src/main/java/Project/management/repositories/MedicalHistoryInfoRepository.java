package Project.management.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Project.management.entities.MedicalHistoryInfo;

@Repository
public interface MedicalHistoryInfoRepository extends JpaRepository<MedicalHistoryInfo, Long> {
}
