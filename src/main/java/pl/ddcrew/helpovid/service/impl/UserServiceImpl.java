package pl.ddcrew.helpovid.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.ddcrew.helpovid.aspect.annotation.CheckUserAvailability;
import pl.ddcrew.helpovid.exception.UserNotFoundException;
import pl.ddcrew.helpovid.model.User;
import pl.ddcrew.helpovid.repository.UserRepository;
import pl.ddcrew.helpovid.service.LocationService;
import pl.ddcrew.helpovid.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    LocationService locationService;

    @Override
    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();
        if(users.isEmpty()) throw new UserNotFoundException();
        else return users;
    }

    @Override
    public boolean checkUserAvailability(User user) {
        if(userRepository.existsByeMail(user.geteMail()) || userRepository.existsByphoneNumber(user.getPhoneNumber())) return false;
        else return true;
    }

    @Override
    @CheckUserAvailability
    public void registerUser(User user) {
        locationService.saveLocation(user.getLocation());
        userRepository.save(user);
    }
}
