package Project.management.components.factories;

import Project.management.dto.MedicalHistoryInfoDTO;
import Project.management.entities.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

/*
*   Factories share the same logic
*   Testing one factory is the equivalent of testing all
*   The update() and create() methods share the same logic, so multiple tests are redundant
*/

@DataJpaTest
public class MedicalHistoryInfoFactoryTests {

    final private MedicalHistoryInfoFactory factory = new MedicalHistoryInfoFactory();

    final private Patient patient = new Patient();
    final private BasicMedicalHistoryInfo testBasicMedicalHistoryInfo = new BasicMedicalHistoryInfo();
    private MedicalHistoryInfoDTO dto = new MedicalHistoryInfoDTO();

    @BeforeEach
    void setUp() {
        patient.setFirstName("slim");
        patient.setLastName("mezani");
        patient.setSexe(Sexe.HOMME);
        patient.setDateBirth(LocalDate.parse("1994-06-04"));
        patient.setPlaceBirth("El Biar");
        patient.setFatherName("Hocine");
        patient.setMotherName("Soraya");
        patient.setAddress("148 lot -F- Draria");
        patient.setCountry(Country.ALGERIE);
        patient.setDepartment("ALGER");
        patient.setCounty("DRARIA");
        patient.setTel1("0551220912");
        patient.setTel2("0551220912");
        patient.setEmail("slim.mezani@gmail.com");

        dto = MedicalHistoryInfoDTO.builder()
                .label("Seafood")
                .type(MedicalHistoryInfoType.ALLERGY)
                .build();

        testBasicMedicalHistoryInfo.setLabel("Seafood");
        testBasicMedicalHistoryInfo.setType(MedicalHistoryInfoType.ALLERGY);
        testBasicMedicalHistoryInfo.setPatient(patient);
    }

    @Test
    public void testCreate() {

        MedicalHistoryInfo medicalHistoryInfo = factory.create(dto, patient);

        assertNotNull(medicalHistoryInfo);
        assertInstanceOf(BasicMedicalHistoryInfo.class, factory.create(dto, patient));
    }

}
