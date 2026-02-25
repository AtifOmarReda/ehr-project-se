package project.login.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.login.entities.Role;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long id;

    @NotNull(message = "Ce champ est obligatoire")
    private String firstName;

    @NotNull(message = "Ce champ est obligatoire")
    private String lastName;

    @NotNull(message = "Ce champ est obligatoire")
    private String email;

    @NotNull(message = "Ce champ est obligatoire")
    private String username;

    @NotNull(message = "Ce champ est obligatoire")
    private Role role;

    private Boolean isDoctor;

}