package application.controller.web;

import application.data.model.Order;
import application.data.service.OrderService;
import application.viewmodel.order.OrderHistoryViewModel;
import application.viewmodel.order.OrderDetailViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping(path = "/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/user")
    public String orderUser(Model model){
        String  username = SecurityContextHolder.getContext().getAuthentication().getName();
        OrderHistoryViewModel vm = new OrderHistoryViewModel();
        vm.setOrderList(orderService.findOrderByUserName(username));
        vm.setUserName(username);
        model.addAttribute("vm",vm);
        return "/user/order-history";
    }


    @GetMapping("/{orderId}")
    public String orderProduct(Model model, @PathVariable int orderId, final Principal principal){
        Order order = orderService.findOne(orderId);
        String  username = SecurityContextHolder.getContext().getAuthentication().getName();
        OrderDetailViewModel vm = new OrderDetailViewModel();
        vm.setOrder(order);
        if(principal!=null) vm.setUserName(username);
        else vm.setUserName("anonymous");
        model.addAttribute("vm",vm);
        return "/user/product-order";
    }

    @GetMapping("/guid")
    public String orderGuid(HttpServletRequest request,Model model){

        Cookie cookie[] = request.getCookies();

        String guid = "";
        OrderHistoryViewModel vm = new OrderHistoryViewModel();

        for(Cookie c : cookie){
            if(c.getName().equals("guid")){
                guid= c.getValue();
            }
        }

        List<Order> orderList = orderService.findOrderByGuid(guid);
        vm.setOrderList(orderList);
        vm.setUserName("anonymous");
        model.addAttribute("vm",vm);

        return "/user/order-guid";
    }
}
