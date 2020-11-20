package pl.ddcrew.helpovid.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.ddcrew.helpovid.exception.UnauthorizedException;
import pl.ddcrew.helpovid.service.SessionService;

import java.util.HashMap;

@RestController
@RequestMapping(value = "/api/login")
public class LoginController {
    @Autowired
    SessionService sessionService;

    @Autowired
    CredentialsAuthorization credentialsAuthorization;

    @PostMapping
    public HashMap<String, String> issueToken(@RequestBody LoginDTO loginDTO){
        String token;
        if(credentialsAuthorization.authorize(loginDTO)){
            token = sessionService.startSession(loginDTO);
        }
        else throw new UnauthorizedException();

        HashMap<String,String> payload = new HashMap<>();
        payload.put("token",token);

        return payload;
    }
}