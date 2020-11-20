package pl.ddcrew.helpovid.service;

import pl.ddcrew.helpovid.model.User;

import java.util.List;

public interface UserService {
    public List<User> getAllUsers();
    public boolean checkUserAvailability(User user);
    public void registerUser(User user);
}
