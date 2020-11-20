package pl.ddcrew.helpovid.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import pl.ddcrew.helpovid.controller.register.RegistrationDataDTO;
import pl.ddcrew.helpovid.model.Location;
import pl.ddcrew.helpovid.model.User;
import pl.ddcrew.helpovid.service.LocationService;

@Component
public class RegistrationDataDTOToUserConverter implements Converter<RegistrationDataDTO, User> {
    @Autowired
    LocationService locationService;

    @Override
    public User convert(RegistrationDataDTO registrationDataDTO) {
        User user = new User();
        user.seteMail(registrationDataDTO.geteMail());
        user.setPhoneNumber(registrationDataDTO.getPhoneNumber());
        user.setName(registrationDataDTO.getName());
        user.setPassword(registrationDataDTO.getPassword());

        Location location = new Location(registrationDataDTO.getLatitude(), registrationDataDTO.getLongitude());
        locationService.saveLocation(location);

        user.setLocation(location);
        return user;
    }
}
