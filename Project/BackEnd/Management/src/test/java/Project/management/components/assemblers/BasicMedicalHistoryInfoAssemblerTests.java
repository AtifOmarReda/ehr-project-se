package Project.management.components.assemblers;

import Project.management.dto.MedicalHistoryInfoDTO;
import Project.management.entities.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/*
*   All assemblers have the same logic
*   that means that testing BasicMedicalHistoryInfoAssembler is the equivalent of testing all assemblers
*/

@DataJpaTest
public class BasicMedicalHistoryInfoAssemblerTests {

    final private BasicMedicalHistoryInfoAssembler assembler = new BasicMedicalHistoryInfoAssembler();

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

        BasicMedicalHistoryInfo basicMedicalHistoryInfo = (BasicMedicalHistoryInfo) assembler.create(dto, patient);

        assertNotNull(basicMedicalHistoryInfo);
        assertEquals(testBasicMedicalHistoryInfo.getLabel(), basicMedicalHistoryInfo.getLabel());
        assertEquals(testBasicMedicalHistoryInfo.getType(), basicMedicalHistoryInfo.getType());
        assertEquals(testBasicMedicalHistoryInfo.getPatient(), basicMedicalHistoryInfo.getPatient());

    }

    @Test
    public void testUpdate() {

        BasicMedicalHistoryInfo basicMedicalHistoryInfo = new BasicMedicalHistoryInfo();

        basicMedicalHistoryInfo.setLabel("Not Seafood");
        basicMedicalHistoryInfo.setType(MedicalHistoryInfoType.ALLERGY);
        basicMedicalHistoryInfo.setPatient(patient);

        assembler.update(basicMedicalHistoryInfo, dto);

        assertNotNull(basicMedicalHistoryInfo);
        assertEquals(testBasicMedicalHistoryInfo.getLabel(), basicMedicalHistoryInfo.getLabel());
        assertEquals(testBasicMedicalHistoryInfo.getType(), basicMedicalHistoryInfo.getType());
        assertEquals(testBasicMedicalHistoryInfo.getPatient(), basicMedicalHistoryInfo.getPatient());

    }
}
