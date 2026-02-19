package project.login.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.login.entities.User;
import project.login.repositories.RefreshTokenRepository;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final RefreshTokenRepository refreshTokenRepository;

    @Transactional
    public void logout(User user) {
        refreshTokenRepository.deleteByUserId(user.getId());
    }

}