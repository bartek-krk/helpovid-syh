package pl.ddcrew.helpovid.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.ddcrew.helpovid.service.SessionService;

@RestController
@RequestMapping(value = "/api/logout")
public class LogoutController {
    @Autowired
    SessionService sessionService;

    @DeleteMapping
    public void logout(@RequestBody LogoutDTO logoutDTO){
        sessionService.terminateSession(logoutDTO);
    }
}
