package Project.management.dto;

import Project.management.entities.Sexe;
import Project.management.entities.FamilySituation;
import Project.management.entities.Country;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PatientDTO {

    @NotBlank(message = "Le prénom est obligatoire")
    @Size(max = 50)
    private String firstName;

    @NotBlank(message = "Le nom est obligatoire")
    @Size(max = 50)
    private String lastName;

    @NotNull(message = "Le sexe est obligatoire")
    private Sexe sexe;

    @NotNull(message = "La date de naissance est obligatoire")
    @Past(message = "La date de naissance doit être dans le passé")
    private LocalDate dateBirth;

    @NotBlank(message = "Le lieu de naissance est obligatoire")
    private String placeBirth;

    @NotNull(message = "La situation familiale est obligatoire")
    private FamilySituation familySituation;

    private String fatherName;
    private String motherName;

    @NotBlank(message = "L'adresse est obligatoire")
    private String address;

    @NotNull(message = "Le pays est obligatoire")
    private Country country;

    private String department;
    private String county;

    @NotBlank(message = "Le téléphone est obligatoire")
    private String tel1;

    private String tel2;

    @Email(message = "Format de l'email invalide")
    @NotBlank(message = "L'email est obligatoire")
    private String email;

}
