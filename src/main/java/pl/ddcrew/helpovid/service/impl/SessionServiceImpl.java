package pl.ddcrew.helpovid.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.ddcrew.helpovid.model.Session;
import pl.ddcrew.helpovid.repository.SessionRepository;
import pl.ddcrew.helpovid.security.LoginDTO;
import pl.ddcrew.helpovid.security.TokenGenerator;
import pl.ddcrew.helpovid.service.SessionService;
import pl.ddcrew.helpovid.service.UserService;

import java.time.Instant;

@Service
public class SessionServiceImpl implements SessionService {
    @Autowired
    SessionRepository sessionRepository;

    @Autowired
    UserService userService;

    @Override
    public String startSession(LoginDTO loginDTO) {
        Session session = new Session();
        session.setUserId(userService.getByPhoneNumber(loginDTO.getPhoneNumber()).getId());
        session.setTimeout(Instant.now().getEpochSecond() + 900);

        String token = TokenGenerator.generate();
        session.setToken(token);

        sessionRepository.save(session);

        return token;
    }
}
