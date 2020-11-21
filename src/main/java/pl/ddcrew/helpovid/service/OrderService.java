package pl.ddcrew.helpovid.service;

import pl.ddcrew.helpovid.model.Order;

import java.util.List;

public interface OrderService {
    public List<Order> getAllOrders();
    public void saveOrder(Order order);
}
