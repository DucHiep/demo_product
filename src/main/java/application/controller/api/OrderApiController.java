package application.controller.api;

import application.data.model.Order;
import application.data.service.OrderService;
import application.model.api.BaseApiResult;
import application.model.api.DataApiResult;
import application.model.api.OrderDataModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/order")
public class OrderApiController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    public BaseApiResult createOrder(@RequestBody OrderDataModel orderDataModel){
        DataApiResult result = new DataApiResult();

        try {
            Order order = new Order();
            order.setAddress(orderDataModel.getAddress());
            order.setName(orderDataModel.getName());
            order.setEmail(orderDataModel.getEmail());
            order.setPhoneNumber(orderDataModel.getPhone());
            orderService.addNewOrder(order);
            result.setData(order.getId());
            result.setMessage("Save order successfully: " + order.getId());
            result.setSuccess(true);
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }
        return result;
    }

}
