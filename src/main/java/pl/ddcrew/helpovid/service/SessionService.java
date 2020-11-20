package pl.ddcrew.helpovid.service;

import pl.ddcrew.helpovid.security.LoginDTO;
import pl.ddcrew.helpovid.security.LogoutDTO;

public interface SessionService {
    public String startSession(LoginDTO loginDTO);
    public void terminateSession(LogoutDTO logoutDTO);
}
