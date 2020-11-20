package pl.ddcrew.helpovid.service;

import pl.ddcrew.helpovid.model.User;
import pl.ddcrew.helpovid.security.LoginDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public List<User> getAllUsers();
    public boolean checkUserAvailability(User user);
    public void registerUser(User user);
    public User getByPhoneNumber(String phoneNumber);
}
