package pl.ddcrew.helpovid.service;

import pl.ddcrew.helpovid.security.LoginDTO;
import pl.ddcrew.helpovid.security.LogoutDTO;
import pl.ddcrew.helpovid.security.TokenAuthDAO;

public interface SessionService {
    public String startSession(LoginDTO loginDTO);
    public void terminateSession(LogoutDTO logoutDTO);
    public boolean tokenAndIdMatches(TokenAuthDAO tokenAuthDAO);
    public boolean isInTime(TokenAuthDAO tokenAuthDAO);
}
