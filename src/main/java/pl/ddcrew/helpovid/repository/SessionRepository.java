package pl.ddcrew.helpovid.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.ddcrew.helpovid.model.Session;

import java.util.Optional;

public interface SessionRepository extends JpaRepository<Session, Long> {
    public boolean existsByUserIdAndToken (Long userId, String token);
    public boolean existsByUserId (Long userId);
    public void deleteByUserIdAndToken(Long userId, String token);
    public Optional<Session> findByUserId(Long userId);
}
