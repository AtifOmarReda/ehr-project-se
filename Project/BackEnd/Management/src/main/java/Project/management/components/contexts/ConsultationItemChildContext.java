package Project.management.components.contexts;

import Project.management.components.strategies.ConsultationItemChildStrategy;
import Project.management.components.strategies.LabExamItemStrategy;
import Project.management.components.strategies.MedicationStrategy;
import Project.management.entities.ConsultationItem;
import org.springframework.stereotype.Component;

/*
*   Context to choose correct strategy
*   Strategies have Add Child logic for ConsultationItem children classes
* */

@Component
public class ConsultationItemChildContext {

    LabExamItemStrategy labExamItemStrategy = new LabExamItemStrategy();
    MedicationStrategy medicationStrategy = new MedicationStrategy();

    public ConsultationItemChildStrategy chooseStrategy(ConsultationItem item) {

        if(labExamItemStrategy.supports(item))
            return labExamItemStrategy;
        else if(medicationStrategy.supports(item))
            return medicationStrategy;
        else
            throw new RuntimeException("Consultation item child strategy doesn't exist!");

    }

}
