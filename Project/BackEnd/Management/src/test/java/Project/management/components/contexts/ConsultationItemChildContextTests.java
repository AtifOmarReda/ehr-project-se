package Project.management.components.contexts;

import Project.management.components.strategies.MedicationStrategy;
import Project.management.entities.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class ConsultationItemChildContextTests {

    final private ConsultationItemChildContext context = new ConsultationItemChildContext();

    @Test
    public void testChooseStrategySuccess() {

        Prescription prescription = new Prescription();
        prescription.setType(ConsultationItemType.PRESCRIPTION);
        prescription.setDate(LocalDate.parse("2026-02-25"));
        prescription.setMedications(new ArrayList<>());

        assertInstanceOf(MedicationStrategy.class, context.chooseStrategy(prescription));

    }

    @Test
    public void testChooseStrategyException() {

        Document document = new Document();
        document.setType(ConsultationItemType.DOCUMENT);
        document.setDocumentName("Hello.pdf");
        document.setDocumentUrl("https://www.hellodocument.com/f/Hello.pdf/");

        RuntimeException exception = assertThrows(RuntimeException.class, () -> context.chooseStrategy(document));
        assertEquals("Consultation item child strategy doesn't exist!", exception.getMessage());

    }

}
