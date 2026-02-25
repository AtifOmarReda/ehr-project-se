package Project.management.repositories;

import Project.management.entities.ConsultationItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultationItemRepository extends JpaRepository<ConsultationItem, Long> {
}
