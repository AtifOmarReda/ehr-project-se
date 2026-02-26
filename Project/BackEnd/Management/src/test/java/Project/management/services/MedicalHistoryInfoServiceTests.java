package Project.management.services;

import Project.management.components.factories.MedicalHistoryInfoFactory;
import Project.management.dto.MedicalHistoryInfoDTO;
import Project.management.dto.PatientDTO;
import Project.management.entities.*;
import Project.management.repositories.MedicalHistoryInfoRepository;
import Project.management.repositories.PatientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MedicalHistoryInfoServiceTests {

    @Mock
    private MedicalHistoryInfoRepository medicalHistoryInfoRepository;

    @Mock
    private PatientRepository patientRepository;

    @Spy
    private MedicalHistoryInfoFactory medicalHistoryInfoFactory;

    @InjectMocks
    private MedicalHistoryInfoService medicalHistoryInfoService;

    private BasicMedicalHistoryInfo testBasicMedicalHistoryInfo;
    private MedicalHistoryInfoDTO dto;

    @BeforeEach
    void setUp() {

        dto = MedicalHistoryInfoDTO.builder()
                .label("Seafood")
                .type(MedicalHistoryInfoType.ALLERGY)
                .patientId(1L)
                .build();

        testBasicMedicalHistoryInfo = new BasicMedicalHistoryInfo();
        testBasicMedicalHistoryInfo.setLabel("Seafood");
        testBasicMedicalHistoryInfo.setType(MedicalHistoryInfoType.ALLERGY);

    }

    @Test
    public void testSaveMedicalHistoryInfoSuccess() {

        when(patientRepository.findById(dto.getPatientId())).thenReturn(Optional.of(new Patient()));
        when(medicalHistoryInfoRepository.save(any(MedicalHistoryInfo.class))).thenAnswer(invocation -> invocation.getArgument(0));

        MedicalHistoryInfo medicalHistoryInfo = medicalHistoryInfoService.saveMedicalHistoryInfo(dto);

        assertNotNull(medicalHistoryInfo);
        assertInstanceOf(BasicMedicalHistoryInfo.class, medicalHistoryInfo);

        BasicMedicalHistoryInfo basicMedicalHistoryInfo = (BasicMedicalHistoryInfo) medicalHistoryInfo;

        assertEquals(testBasicMedicalHistoryInfo.getType(), basicMedicalHistoryInfo.getType());
        assertEquals(testBasicMedicalHistoryInfo.getLabel(), basicMedicalHistoryInfo.getLabel());

    }

    @Test
    public void testSaveMedicalHistoryInfoException() {

        RuntimeException exception = assertThrows(RuntimeException.class, () -> medicalHistoryInfoService.saveMedicalHistoryInfo(dto));
        assertEquals("Patient does not exist", exception.getMessage());

    }

    @Test
    public void testUpdateSuccess() {

        testBasicMedicalHistoryInfo.setLabel("Pollen");

        BasicMedicalHistoryInfo basicMedicalHistoryInfo = new BasicMedicalHistoryInfo();
        basicMedicalHistoryInfo.setLabel("Pollen");
        basicMedicalHistoryInfo.setType(MedicalHistoryInfoType.ALLERGY);

        when(medicalHistoryInfoRepository.findById(basicMedicalHistoryInfo.getId())).thenReturn(Optional.of(basicMedicalHistoryInfo));
        when(medicalHistoryInfoRepository.save(any(MedicalHistoryInfo.class))).thenAnswer(invocation -> invocation.getArgument(0));

        MedicalHistoryInfo medicalHistoryInfo = medicalHistoryInfoService.update(basicMedicalHistoryInfo.getId(), dto);

        assertNotNull(medicalHistoryInfo);
        assertInstanceOf(BasicMedicalHistoryInfo.class, medicalHistoryInfo);

        BasicMedicalHistoryInfo updatedBasicMedicalHistoryInfo = (BasicMedicalHistoryInfo) medicalHistoryInfo;

        assertEquals(testBasicMedicalHistoryInfo.getType(), updatedBasicMedicalHistoryInfo.getType());
        assertNotEquals(testBasicMedicalHistoryInfo.getLabel(), updatedBasicMedicalHistoryInfo.getLabel());

    }

    @Test
    public void testUpdateMedicalHistoryInfoException() {

        RuntimeException exception = assertThrows(RuntimeException.class, () -> medicalHistoryInfoService.update(testBasicMedicalHistoryInfo.getId(), dto));
        assertEquals("Medical history info doesn't exist!", exception.getMessage());

    }

    // No need to test delete because logic is the same as PatientService

}
