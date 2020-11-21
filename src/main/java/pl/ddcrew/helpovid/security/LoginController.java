package pl.ddcrew.helpovid.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.ddcrew.helpovid.exception.UnauthorizedException;
import pl.ddcrew.helpovid.service.SessionService;
import pl.ddcrew.helpovid.service.UserService;

import java.util.HashMap;

@RestController
@RequestMapping(value = "/api/login")
@CrossOrigin(value = "*")
public class LoginController {
    @Autowired
    SessionService sessionService;

    @Autowired
    CredentialsAuthorization credentialsAuthorization;

    @Autowired
    UserService userService;

    @PostMapping
    public HashMap<String, String> issueToken(@RequestBody LoginDTO loginDTO){
        String token;
        if(credentialsAuthorization.authorize(loginDTO)){
            token = sessionService.startSession(loginDTO);
        }
        else throw new UnauthorizedException();

        HashMap<String,String> payload = new HashMap<>();
        payload.put("token",token);
        payload.put("id", String.valueOf(userService.getByPhoneNumber(loginDTO.getPhoneNumber()).getId()));

        return payload;
    }
}