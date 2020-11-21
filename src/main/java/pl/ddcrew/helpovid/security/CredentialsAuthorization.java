package pl.ddcrew.helpovid.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.ddcrew.helpovid.exception.UnauthorizedException;
import pl.ddcrew.helpovid.model.User;
import pl.ddcrew.helpovid.service.UserService;

@Service
public class CredentialsAuthorization {
    @Autowired
    private UserService userService;

    public boolean authorize(LoginDTO loginDTO){
        User user = userService.getByPhoneNumber(loginDTO.getPhoneNumber());
        if(user.getPassword().equals(loginDTO.getPassword())) return true;
        else throw new UnauthorizedException();
    }
}
