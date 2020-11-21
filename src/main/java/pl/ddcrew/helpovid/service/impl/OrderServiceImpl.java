package pl.ddcrew.helpovid.service.impl;

import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.ddcrew.helpovid.exception.OrderNotFoundException;
import pl.ddcrew.helpovid.exception.UnsupportedMediaTypeException;
import pl.ddcrew.helpovid.model.Order;
import pl.ddcrew.helpovid.model.User;
import pl.ddcrew.helpovid.repository.OrderRepository;
import pl.ddcrew.helpovid.service.OrderService;
import pl.ddcrew.helpovid.service.UserService;
import pl.ddcrew.helpovid.utility.DistanceCalculator;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserService userService;

    @Override
    public List<Order> getAllOrders(User user) {
        List<Order> orders = orderRepository.findAll();
        for(Order order:orders){
            order.setDistance(DistanceCalculator.calculateDistance(order.getOwner().getLocation(),user.getLocation()));
        }
        if (orders.isEmpty()) throw new OrderNotFoundException();
        else return orders;
    }

    @Override
    public void saveOrder(Order order) {
        if(order.getTitle() == null ||
        order.getContent() == null ||
        order.getOwner() == null) throw new UnsupportedMediaTypeException();
        orderRepository.save(order);
    }

    public void assignOrder(Long orderId, Long userId){
        User user = userService.getById(userId);
        Order order = this.getById(orderId);
        order.setAssignee(user);
        orderRepository.save(order);
    }

    @Override
    public Order getById(Long id) {
        Order order = orderRepository.getById(id).orElse(null);
        if(order == null) throw new OrderNotFoundException();
        else return order;
    }
}
