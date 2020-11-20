package pl.ddcrew.helpovid.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.ddcrew.helpovid.model.Location;

public interface LocationRepository extends JpaRepository<Location, Long> {
}
