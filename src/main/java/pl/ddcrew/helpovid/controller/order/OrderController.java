package pl.ddcrew.helpovid.controller.order;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.ddcrew.helpovid.aspect.annotation.RestrictedAccess;
import pl.ddcrew.helpovid.command.OrderDataDAOToOrderConverter;
import pl.ddcrew.helpovid.exception.UnsupportedMediaTypeException;
import pl.ddcrew.helpovid.model.Order;
import pl.ddcrew.helpovid.model.User;
import pl.ddcrew.helpovid.security.TokenAuthDAO;
import pl.ddcrew.helpovid.service.OrderService;
import pl.ddcrew.helpovid.service.UserService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/order")
public class OrderController {
    @Autowired
    OrderService orderService;

    @Autowired
    UserService userService;

    @Autowired
    OrderDataDAOToOrderConverter orderDataDAOToOrderConverter;

    @GetMapping(value = "/all")
    @RestrictedAccess
    public List<Order> getOrders(@RequestBody Map<String,Object> payload){
        ObjectMapper om = new ObjectMapper();
        TokenAuthDAO tokenAuthDAO = om.convertValue(payload.get("credentials"), TokenAuthDAO.class);
        User user = userService.getById(tokenAuthDAO.getUserId());
        return orderService.getAllOrders(user);
    }

    @PostMapping(value = "/place")
    @RestrictedAccess
    public void placeOrder(@RequestBody  Map<String,Object> payload){
        ObjectMapper om = new ObjectMapper();
        OrderDataDAO orderDataDAO;
        if(payload.containsKey("order")) orderDataDAO = om.convertValue(payload.get("order"), OrderDataDAO.class);
        else throw new UnsupportedMediaTypeException();
        TokenAuthDAO tokenAuthDAO = om.convertValue(payload.get("credentials"), TokenAuthDAO.class);
        orderDataDAO.setOwnerId(tokenAuthDAO.getUserId());
        orderService.saveOrder(orderDataDAOToOrderConverter.convert(orderDataDAO));
    }

    @PutMapping(value = "/take")
    @RestrictedAccess
    public void takeOrder(@RequestBody  Map<String,Object> payload){
        ObjectMapper om = new ObjectMapper();
        TokenAuthDAO tokenAuthDAO = om.convertValue(payload.get("credentials"), TokenAuthDAO.class);
        Long orderId = new Long((Integer)payload.get("orderId"));
        orderService.assignOrder(orderId, tokenAuthDAO.getUserId());
    }

}
