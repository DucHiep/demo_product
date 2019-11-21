package application.controller.web;


import application.data.model.*;
import application.data.service.*;
import application.viewmodel.cart.CartViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private CartProductService cartProductService;

    @Autowired
    private ProductOrderService productOrderService;

    @Autowired
    private MaterialService materialService;

    @GetMapping("/cart")
    public String cart(Model model, HttpServletResponse response, HttpServletRequest request) {

        Cookie cookie[] = request.getCookies();

        int cartid=0;

        for (Cookie c : cookie){
            if(c.getName().equals("cartid")){
                cartid=Integer.parseInt(c.getValue());
            }
        }

        CartViewModel vm = new CartViewModel();
        Cart cart = cartService.findOne(cartid);


        int sum=0;

        for(CartProduct cartProduct : cart.getListCartProducts()){
            sum+=cartProduct.price();
        }

        cart.setPrice(sum);
        vm.setCart(cart);

        model.addAttribute("vm",vm);

        return "shoppingcart";
    }

    @GetMapping("/checkout")
    public String checkout( Model model) {
        model.addAttribute("order",new Order());
        return "checkout";
    }

    @RequestMapping(value = "/checkout", method = RequestMethod.POST)
    public String checkOutPost (@Valid @ModelAttribute Order order, HttpServletRequest request, HttpServletResponse response){

        Cookie cookie[] = request.getCookies();

        int cartid =0;

        String guid ="";

        if(cookie!=null){
            for (Cookie c : cookie){
                if(c.getName().equals("cartid")){
                    cartid=Integer.parseInt(c.getValue());
                }
                if(c.getName().equals("guid")){
                    guid= c.getValue();
                }
            }
        }

        if(cartid!=0) {
            Cart cart = cartService.findOne(cartid);
            if(cart.getUserName()!=null)    order.setUserName(cart.getUserName());
            order.setGuid(cart.getGuid());

            orderService.addNewOrder(order);

            List<ProductOrder> productOrders = new ArrayList<>();

            int sum=0;

            for(CartProduct cartProduct : cart.getListCartProducts()){
                ProductOrder productOrder = new ProductOrder();
                productOrder.setAmount(cartProduct.getAmount());
                productOrder.setProduct(cartProduct.getProduct());
                productOrder.setOrder(order);
                productOrders.add(productOrder);
                List<Material> materials = new ArrayList<>();
                for(Material material : cartProduct.getProduct().getListMaterials()){
                    int amount= material.getAmount() - cartProduct.getAmount();
                    material.setAmount(amount);
                    materials.add(material);
                }
                materialService.addNewListMaterials(materials);
                sum= sum + productOrder.price();
            }

            order.setPrice(sum);
            order.setCreatedDate(new Date());

            orderService.addNewOrder(order);

            productOrderService.addNewListProductOrders(productOrders);

            cartProductService.deteleListCartProduct(cart.getListCartProducts());

            cartService.deteleCart(cart.getId());



            Cookie cookie2 = new Cookie("cartid",null);
            cookie2.setHttpOnly(true);
            cookie2.setMaxAge(0);
            response.addCookie(cookie2);


        }

        return "redirect:/";
    }
}
