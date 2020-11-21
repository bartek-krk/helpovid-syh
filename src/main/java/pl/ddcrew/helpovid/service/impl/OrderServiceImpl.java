package pl.ddcrew.helpovid.service.impl;

import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.ddcrew.helpovid.exception.OrderNotFoundException;
import pl.ddcrew.helpovid.model.Order;
import pl.ddcrew.helpovid.repository.OrderRepository;
import pl.ddcrew.helpovid.service.OrderService;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository orderRepository;

    @Override
    public List<Order> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        if (orders.isEmpty()) throw new OrderNotFoundException();
        else return orders;
    }

    @Override
    public void saveOrder(Order order) {
        orderRepository.save(order);
    }
}
