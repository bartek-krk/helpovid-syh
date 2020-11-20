package pl.ddcrew.helpovid.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.ddcrew.helpovid.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    public boolean existsByeMail(String eMail);
    public boolean existsByphoneNumber(String phoneNumber);
    public Optional<User> getByphoneNumber(String phoneNumber);
}
