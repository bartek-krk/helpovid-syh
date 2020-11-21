package pl.ddcrew.helpovid.service;

import pl.ddcrew.helpovid.model.Order;
import pl.ddcrew.helpovid.model.User;

import java.util.List;

public interface OrderService {
    public List<Order> getAllOrders(User user);
    public void saveOrder(Order order);
    public Order getById(Long id);
    public void assignOrder(Long orderId, Long userId);
}
