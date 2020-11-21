package pl.ddcrew.helpovid.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.ddcrew.helpovid.exception.UnauthorizedException;
import pl.ddcrew.helpovid.exception.UserNotFoundException;
import pl.ddcrew.helpovid.model.Session;
import pl.ddcrew.helpovid.repository.SessionRepository;
import pl.ddcrew.helpovid.security.LoginDTO;
import pl.ddcrew.helpovid.security.LogoutDTO;
import pl.ddcrew.helpovid.security.TokenAuthDAO;
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
        int TIMEOUT = 900; //seconds

        Session session = new Session();
        session.setUserId(userService.getByPhoneNumber(loginDTO.getPhoneNumber()).getId());
        session.setTimeout(Instant.now().getEpochSecond() + TIMEOUT);

        String token = TokenGenerator.generate();
        session.setToken(token);

        sessionRepository.save(session);

        return token;
    }

    @Override
    @Transactional
    public void terminateSession(LogoutDTO logoutDTO) {
        if(sessionRepository.existsByUserIdAndToken(logoutDTO.getUserId(), logoutDTO.getToken())){
            sessionRepository.deleteByUserIdAndToken(logoutDTO.getUserId(), logoutDTO.getToken());
        }
        else throw new UnauthorizedException();
    }

    @Override
    public boolean tokenAndIdMatches(TokenAuthDAO tokenAuthDAO){
        return sessionRepository.existsByUserIdAndToken(tokenAuthDAO.getUserId(), tokenAuthDAO.getToken());
    }

    @Override
    public boolean isInTime(TokenAuthDAO tokenAuthDAO) {
        if(sessionRepository.existsByUserId(tokenAuthDAO.getUserId())){
            if(sessionRepository.findByUserIdAndToken(tokenAuthDAO.getUserId(), tokenAuthDAO.getToken()).orElse(null).getTimeout() > Instant.now().getEpochSecond()){
                return true;
            }
            else return false;
        }
        else throw new UserNotFoundException();
    }
}
