package Project.management.security;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserPrincipal {

    private final Long id;
    private final String username;
    private final String role;
    private final boolean isDoctor;

}