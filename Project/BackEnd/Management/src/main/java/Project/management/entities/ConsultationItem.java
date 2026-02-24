package Project.management.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Inheritance;
import jakarta.persistence.Id;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import lombok.Setter;


// Abstract class used for more specific ConsultationItem types

@Entity
@Table(name = "consultation_item")
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class ConsultationItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "consultationId")
    private Consultation consultation;
}
