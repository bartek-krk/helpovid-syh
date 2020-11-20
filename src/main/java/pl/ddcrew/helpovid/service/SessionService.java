package pl.ddcrew.helpovid.service;

import pl.ddcrew.helpovid.security.LoginDTO;

public interface SessionService {
    public String startSession(LoginDTO loginDTO);
}
