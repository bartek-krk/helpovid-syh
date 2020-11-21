package pl.ddcrew.helpovid.controller.register;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.ddcrew.helpovid.command.RegistrationDataDTOToUserConverter;
import pl.ddcrew.helpovid.service.UserService;

@RestController
@RequestMapping(value = "/api/register")
@CrossOrigin(value = "*")
public class RegisterController {
    @Autowired
    UserService userService;

    @Autowired
    RegistrationDataDTOToUserConverter converter;

    @PostMapping
    public void register(@RequestBody RegistrationDataDTO registrationDataDTO){
        userService.registerUser(converter.convert(registrationDataDTO));
    }
}
