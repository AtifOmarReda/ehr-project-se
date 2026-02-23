package Project.management.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Project.management.entities.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
}
