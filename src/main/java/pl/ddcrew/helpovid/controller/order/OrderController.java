package pl.ddcrew.helpovid.controller.order;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.ddcrew.helpovid.aspect.annotation.RestrictedAccess;
import pl.ddcrew.helpovid.command.OrderDataDAOToOrderConverter;
import pl.ddcrew.helpovid.exception.UnsupportedMediaTypeException;
import pl.ddcrew.helpovid.model.Order;
import pl.ddcrew.helpovid.security.TokenAuthDAO;
import pl.ddcrew.helpovid.service.OrderService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/order")
public class OrderController {
    @Autowired
    OrderService orderService;

    @Autowired
    OrderDataDAOToOrderConverter orderDataDAOToOrderConverter;

    @GetMapping
    public List<Order> getOrders(){
        return orderService.getAllOrders();
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
}
