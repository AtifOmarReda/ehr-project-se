package Project.management.services;

import Project.management.entities.Patient;
import Project.management.repositories.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;

    public Patient savePatient(Patient patient) {
        return patientRepository.save(patient);
    }

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public Optional<Patient> getPatientById(Long id) {
        return patientRepository.findById(id);
    }

    @Transactional
    public Patient updatePatient(Long id, Patient patientDetails) {
        return patientRepository.findById(id).map(existingPatient -> {
            // On met à jour les champs nécessaires (éviter de changer l'ID)
            existingPatient.setFirstName(patientDetails.getFirstName());
            existingPatient.setLastName(patientDetails.getLastName());
            existingPatient.setSexe(patientDetails.getSexe());
            existingPatient.setDateBirth(patientDetails.getDateBirth());
            existingPatient.setPlaceBirth(patientDetails.getPlaceBirth());
            existingPatient.setFamilySituation(patientDetails.getFamilySituation());
            existingPatient.setFatherName(patientDetails.getFatherName());
            existingPatient.setMotherName(patientDetails.getMotherName());
            existingPatient.setAddress(patientDetails.getAddress());
            existingPatient.setCountry(patientDetails.getCountry());
            existingPatient.setDepartment(patientDetails.getDepartment());
            existingPatient.setCounty(patientDetails.getCounty());
            existingPatient.setTel1(patientDetails.getTel1());
            existingPatient.setTel2(patientDetails.getTel2());
            existingPatient.setEmail(patientDetails.getEmail());
            return patientRepository.save(existingPatient);
        }).orElseThrow(() -> new RuntimeException("Patient not found with ID : " + id));
    }

    public void deletePatient(Long id) {
        if (!patientRepository.existsById(id)) {
            throw new RuntimeException("Unable to delete: Patient not found");
        }
        patientRepository.deleteById(id);
    }

}