package pl.ddcrew.helpovid.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import pl.ddcrew.helpovid.controller.order.OrderDataDAO;
import pl.ddcrew.helpovid.model.Order;
import pl.ddcrew.helpovid.service.UserService;

@Component
public class OrderDataDAOToOrderConverter implements Converter<OrderDataDAO, Order> {
    @Autowired
    UserService userService;

    @Override
    public Order convert(OrderDataDAO orderDataDAO) {
        Order order = new Order();
        order.setTitle(orderDataDAO.getTitle());
        order.setContent(orderDataDAO.getContent());
        order.setOwner(userService.getById(orderDataDAO.getOwnerId()));
        return order;
    }
}
