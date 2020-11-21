package pl.ddcrew.helpovid.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.ddcrew.helpovid.model.Order;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
