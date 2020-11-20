package pl.ddcrew.helpovid.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.ddcrew.helpovid.model.Session;

public interface SessionRepository extends JpaRepository<Session, Long> {
    public boolean existsByUserIdAndToken (Long userId, String token);
    public void deleteByUserIdAndToken(Long userId, String token);
}
