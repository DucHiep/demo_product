package application.controller.web;

import application.data.model.Cart;
import application.data.model.Order;
import application.data.model.Product;
import application.data.model.User;
import application.data.service.CartService;
import application.data.service.OrderService;
import application.data.service.ProductCategoryService;
import application.data.service.UserService;
import application.extension.ProductPriceComparatorDecrease;
import application.extension.ProductPriceComparatorIncrease;
import application.viewmodel.list_product_category.ListProductCategoryViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Controller
public class ProductCategoryController {

    @Autowired
    private ProductCategoryService productCategoryService;

    @Autowired
    private CartService cartService;

    @GetMapping(path="/")
    public String index(Model model,
                        @RequestParam(value = "categoryId", required = false)
                                   String productCategoryId,
                        @RequestParam(value = "sortBy", required = false)
                                String sortBy,
                        HttpServletResponse response,
                        HttpServletRequest request,
                        final Principal principal){

        String  username = SecurityContextHolder.getContext().getAuthentication().getName();
        if(principal == null) System.out.println("an danh");
        else System.out.println(username);

        ListProductCategoryViewModel vm = new ListProductCategoryViewModel();


       if(principal!=null){
           Cookie cookie[] = request.getCookies();

           int kt1 = 0;
           int kt2 = 0;
           int kt3 = 0;

           String guid_1 = "";

           for(Cookie c : cookie){
               if(c.getName().equals("username")){
                   kt1 = 1;
               }
               if(c.getName().equals("guid")){
                   kt2 = 1;
                   guid_1 = c.getValue();
               }
               if(c.getName().equals("cartid")){
                   kt3 = 1;
               }
           }

           if(kt1 == 0){
               Cookie cookie1 = new Cookie("username",username);
               response.addCookie(cookie1);

               List<Cart> carts = cartService.findByUserName(username);
               if(carts.size()>0){
                   String guid_2 = carts.get(0).getGuid();
                   Cookie cookie2 = new Cookie("guid",guid_2);
                   response.addCookie(cookie2);

                   Cookie cookie3 = new Cookie("cartid",Integer.toString(carts.get(0).getId()));
                   response.addCookie(cookie3);

               }else {
                   UUID uuid = UUID.randomUUID();
                   String guid = uuid.toString();

                   Cart cart = new Cart();
                   cart.setUserName(username);
                   cart.setGuid(guid);
                   cartService.addNewCart(cart);
                   System.out.println(cart.getUserName());
                   System.out.println(cart.getGuid());

                   Cookie cookie2 = new Cookie("guid", guid);
                   response.addCookie(cookie2);

                   Cookie cookie3 = new Cookie("cartid",Integer.toString(cart.getId()));
                   response.addCookie(cookie3);
               }
           }else  {
               if (kt2 == 0 ){
                   UUID uuid = UUID.randomUUID();
                   String guid = uuid.toString();

                   Cart cart = new Cart();
                   cart.setUserName(username);
                   cart.setGuid(guid);
                   cartService.addNewCart(cart);

                   Cookie cookie1 = new Cookie("guid", guid);
                   response.addCookie(cookie1);

                   Cookie cookie2 = new Cookie("cartid",Integer.toString(cart.getId()));
                   response.addCookie(cookie2);
               }
           }

           if(kt1!=0&&kt2!=0&&kt3==0){
               Cart cart = new Cart();
               cart.setUserName(username);
               cart.setGuid(guid_1);
               cartService.addNewCart(cart);
               Cookie cookie2 = new Cookie("cartid",Integer.toString(cart.getId()));
               response.addCookie(cookie2);
           }

       }else {
           Cookie cookie[] = request.getCookies();
           int kt1=0;
           int kt2=0;
           String guid2 ="";
           if(cookie!=null){
               for (Cookie c : cookie){
                   if(c.getName().equals("guid")){
                       kt1=1;
                       guid2= c.getValue();
                   }
                   if(c.getName().equals("cartid")){
                       kt2=1;
                   }
               }
           }


           if(kt1==0){
               UUID uuid = UUID.randomUUID();
               String guid = uuid.toString();

               Cart cart = new Cart();
               cart.setGuid(guid);
               cartService.addNewCart(cart);

               Cookie cookie1 = new Cookie("guid",guid);
               response.addCookie(cookie1);

               Cookie cookie2 = new Cookie("cartid", Integer.toString(cart.getId()));
               response.addCookie(cookie2);
           }

           if(kt1==1&&kt2==0){
               Cart cart = new Cart();
               cart.setGuid(guid2);
               cartService.addNewCart(cart);

               Cookie cookie2 = new Cookie("cartid", Integer.toString(cart.getId()));
               response.addCookie(cookie2);

           }

       }

       int categoryId;

       if(productCategoryId!=null) categoryId = Integer.parseInt(productCategoryId);
       else categoryId=1;

       vm.setProductCategory(productCategoryService.findOne(categoryId));

       List<Product> products = vm.getProductCategory().getListProducts();

       if(sortBy!=null){
           if(sortBy.equals("increase")){
               Collections.sort(products, new ProductPriceComparatorIncrease());
           }else {
                Collections.sort(products, new ProductPriceComparatorDecrease());
           }
       }
       vm.setProductList(products);

       vm.setProductCategoryList(productCategoryService.getListAllProductCategories());

       model.addAttribute("vm",vm);


        return "/menu";
    }

}


