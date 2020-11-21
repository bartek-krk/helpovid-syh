package pl.ddcrew.helpovid.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.ddcrew.helpovid.model.Order;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order,Long> {
    public Optional<Order> getById(Long id);
}
