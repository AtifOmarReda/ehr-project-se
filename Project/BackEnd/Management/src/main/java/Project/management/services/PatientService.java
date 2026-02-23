package Project.management.services;

import Project.management.dto.PatientDTO;
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

    // Méthode de conversion (Mappage)
    private Patient mapToEntity(PatientDTO dto) {
        return Patient.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .sexe(dto.getSexe())
                .dateBirth(dto.getDateBirth())
                .placeBirth(dto.getPlaceBirth())
                .familySituation(dto.getFamilySituation())
                .fatherName(dto.getFatherName())
                .motherName(dto.getMotherName())
                .address(dto.getAddress())
                .country(dto.getCountry())
                .department(dto.getDepartment())
                .county(dto.getCounty())
                .tel1(dto.getTel1())
                .tel2(dto.getTel2())
                .email(dto.getEmail())
                .build();
    }

    public Patient savePatient(PatientDTO patientDTO) {
        Patient patient = mapToEntity(patientDTO);
        return patientRepository.save(patient);
    }

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public Optional<Patient> getPatientById(Long id) {
        return patientRepository.findById(id);
    }

    @Transactional
    public Patient updatePatient(Long id, PatientDTO dto) {
        return patientRepository.findById(id).map(existing -> {
            existing.setFirstName(dto.getFirstName());
            existing.setLastName(dto.getLastName());
            existing.setSexe(dto.getSexe());
            existing.setDateBirth(dto.getDateBirth());
            existing.setPlaceBirth(dto.getPlaceBirth());
            existing.setFamilySituation(dto.getFamilySituation());
            existing.setFatherName(dto.getFatherName());
            existing.setMotherName(dto.getMotherName());
            existing.setAddress(dto.getAddress());
            existing.setCountry(dto.getCountry());
            existing.setDepartment(dto.getDepartment());
            existing.setCounty(dto.getCounty());
            existing.setTel1(dto.getTel1());
            existing.setTel2(dto.getTel2());
            existing.setEmail(dto.getEmail());
            return patientRepository.save(existing);
        }).orElseThrow(() -> new RuntimeException("Patient not found"));
    }

    public void deletePatient(Long id) {
        if (!patientRepository.existsById(id)) {
            throw new RuntimeException("Unable to delete: Patient not found");
        }
        patientRepository.deleteById(id);
    }

}