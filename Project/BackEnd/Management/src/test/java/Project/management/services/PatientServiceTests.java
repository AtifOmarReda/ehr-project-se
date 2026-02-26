package Project.management.services;

import Project.management.dto.MedicalHistoryInfoDTO;
import Project.management.dto.PatientDTO;
import Project.management.entities.*;
import Project.management.repositories.PatientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PatientServiceTests {

    @Mock
    private PatientRepository patientRepository;

    @InjectMocks
    private PatientService patientService;

    private Patient testPatient;
    private PatientDTO dto;

    @BeforeEach
    void setUp() {

        dto = PatientDTO.builder()
                .firstName("slim")
                .lastName("mezani")
                .sexe(Sexe.HOMME)
                .dateBirth(LocalDate.parse("1994-06-04"))
                .placeBirth("El Biar").fatherName("Hocine")
                .familySituation(FamilySituation.Célibataire)
                .fatherName("Hocine")
                .motherName("Soraya")
                .address("148 lot -F- Draria")
                .country(Country.ALGERIE)
                .department("ALGER")
                .county("DRARIA")
                .tel1("0551220912")
                .tel2("0551220912")
                .email("slim.mezani@gmail.com")
                .build();

        testPatient = new Patient();
        testPatient.setFirstName("slim");
        testPatient.setLastName("mezani");
        testPatient.setSexe(Sexe.HOMME);
        testPatient.setDateBirth(LocalDate.parse("1994-06-04"));
        testPatient.setPlaceBirth("El Biar");
        testPatient.setFamilySituation(FamilySituation.Célibataire);
        testPatient.setFatherName("Hocine");
        testPatient.setMotherName("Soraya");
        testPatient.setAddress("148 lot -F- Draria");
        testPatient.setCountry(Country.ALGERIE);
        testPatient.setDepartment("ALGER");
        testPatient.setCounty("DRARIA");
        testPatient.setTel1("0551220912");
        testPatient.setTel2("0551220912");
        testPatient.setEmail("slim.mezani@gmail.com");

    }

    @Test
    public void testSavePatient() {

        when(patientRepository.save(any(Patient.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Patient patient = patientService.savePatient(dto);

        assertNotNull(patient);
        assertEquals(testPatient.getFirstName(), patient.getFirstName());
        assertEquals(testPatient.getCounty(), patient.getCounty());
        assertEquals(testPatient.getCountry(), patient.getCountry());
        assertEquals(testPatient.getAddress(), patient.getAddress());
        assertEquals(testPatient.getDepartment(), patient.getDepartment());
        assertEquals(testPatient.getEmail(), patient.getEmail());
        assertEquals(testPatient.getSexe(), patient.getSexe());
        assertEquals(testPatient.getDateBirth(), patient.getDateBirth());
        assertEquals(testPatient.getFamilySituation(), patient.getFamilySituation());
        assertEquals(testPatient.getTel1(), patient.getTel1());
        assertEquals(testPatient.getTel2(), patient.getTel2());
        assertEquals(testPatient.getFatherName(), patient.getFatherName());
        assertEquals(testPatient.getMotherName(), patient.getMotherName());
        assertEquals(testPatient.getLastName(), patient.getLastName());

    }

    @Test
    public void testUpdatePatientSuccess() {

        testPatient.setFirstName("cherif");

        Patient patient = new Patient();
        patient.setFirstName("cherif");
        patient.setLastName("mezani");
        patient.setSexe(Sexe.HOMME);
        patient.setDateBirth(LocalDate.parse("1994-06-04"));
        patient.setPlaceBirth("El Biar");
        patient.setFamilySituation(FamilySituation.Célibataire);
        patient.setFatherName("Hocine");
        patient.setMotherName("Soraya");
        patient.setAddress("148 lot -F- Draria");
        patient.setCountry(Country.ALGERIE);
        patient.setDepartment("ALGER");
        patient.setCounty("DRARIA");
        patient.setTel1("0551220912");
        patient.setTel2("0551220912");
        patient.setEmail("slim.mezani@gmail.com");

        when(patientRepository.findById(patient.getId())).thenReturn(Optional.of(patient));
        when(patientRepository.save(any(Patient.class))).thenAnswer(invocation -> invocation.getArgument(0));

        patient = patientService.updatePatient(patient.getId(), dto);

        assertNotNull(patient);
        assertNotEquals(testPatient.getFirstName(), patient.getFirstName());
        assertEquals(testPatient.getCounty(), patient.getCounty());
        assertEquals(testPatient.getCountry(), patient.getCountry());
        assertEquals(testPatient.getAddress(), patient.getAddress());
        assertEquals(testPatient.getDepartment(), patient.getDepartment());
        assertEquals(testPatient.getEmail(), patient.getEmail());
        assertEquals(testPatient.getSexe(), patient.getSexe());
        assertEquals(testPatient.getDateBirth(), patient.getDateBirth());
        assertEquals(testPatient.getFamilySituation(), patient.getFamilySituation());
        assertEquals(testPatient.getTel1(), patient.getTel1());
        assertEquals(testPatient.getTel2(), patient.getTel2());
        assertEquals(testPatient.getFatherName(), patient.getFatherName());
        assertEquals(testPatient.getMotherName(), patient.getMotherName());
        assertEquals(testPatient.getLastName(), patient.getLastName());

    }

    @Test
    public void testUpdatePatientException() {

        RuntimeException exception = assertThrows(RuntimeException.class, () -> patientService.updatePatient(testPatient.getId(), dto));
        assertEquals("Patient not found", exception.getMessage());

    }

    @Test
    public void testDeletePatientSuccess() {

        Long patientId = 1L;
        when(patientRepository.existsById(anyLong())).thenReturn(true);

        patientService.deletePatient(patientId);
        verify(patientRepository, times(1)).deleteById(patientId);

    }

    @Test
    public void testDeletePatientException() {

        Long patientId = 1L;
        when(patientRepository.existsById(anyLong())).thenReturn(false);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> patientService.deletePatient(patientId));
        assertEquals("Unable to delete: Patient not found", exception.getMessage());

    }

}
