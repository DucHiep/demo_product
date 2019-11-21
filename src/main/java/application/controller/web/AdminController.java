package application.controller.web;

import application.constant.Constant;
import application.data.model.*;
import application.data.service.*;
import application.model.api.MaterialName;
import application.model.api.ProductName;
import application.viewmodel.admin.AdminMaterialVM;
import application.viewmodel.admin.AdminOrderVM;
import application.viewmodel.admin.AdminProductVM;
import application.viewmodel.productdetail.ProductSearchVM;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path = "/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private ContactService contactService;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductCategoryService  productCategoryService;

    @Autowired
    private MaterialService materialService;

    @Autowired
    private BookerService bookerService;

    @Autowired
    private MaterialHistoryService materialHistoryService;

    @GetMapping("")
    public String admin(Model model){
        String  username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findUserByUsername(username);
        model.addAttribute("user",user);
        return "/admin/home";
    }

    @RequestMapping(path = "/product", method = RequestMethod.GET)
    public String product(Model model,
                          @RequestParam(value="pageNumber", required=false) Integer pageNumber) {

        AdminProductVM vm = new AdminProductVM();

        int pageSize = Constant.DEFAULT_PAGE_SIZE;
        if(pageNumber == null) {
            pageNumber = 1;
        }
        PaginableItemList<Product> paginableItemList = productService.getListProducts(Constant.DEFAULT_PAGE_SIZE,
                    pageNumber - 1);

        vm.setProductList(paginableItemList.getListData());

        int totalPages = 0;
        if(paginableItemList.getTotalProducts() % pageSize == 0) {
            totalPages = (int)(paginableItemList.getTotalProducts() / pageSize);
        } else {
            totalPages = (int)(paginableItemList.getTotalProducts() / pageSize) + 1;
        }

        vm.setTotalPagingItems(totalPages);
        vm.setCurrentPage(pageNumber);
        vm.setProductCategoryList(productCategoryService.getListAllProductCategories());

        model.addAttribute("vm",vm);

        return "/admin/tables/product";
    }


    @RequestMapping(path = "/product/search", method = RequestMethod.GET)
    public String searchProduct(@ModelAttribute ProductName productName, BindingResult errors, Model model) {
        try {
            AdminProductVM vm = new AdminProductVM();
            vm.setProductCategoryList(productCategoryService.getListAllProductCategories());
            vm.setProductList(productService.findByNameContaining(productName.getName()));
            vm.setKeyWord(productName.getName());
            model.addAttribute("vm",vm);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/admin/tables/product-search";
    }


    @RequestMapping(path = "/material", method = RequestMethod.GET)
    public String material(Model model,
                           @RequestParam(value="pageNumber", required=false) Integer pageNumber){
        AdminMaterialVM vm = new AdminMaterialVM();
        int pageSize = Constant.DEFAULT_PAGE_SIZE;
        if(pageNumber == null) {
            pageNumber = 1;
        }

        PaginableItemList<Material> paginableItemList = materialService.getListPageMaterials(Constant.DEFAULT_PAGE_SIZE,
                pageNumber - 1);

        vm.setMaterialList(paginableItemList.getListData());

        int totalPages = 0;
        if(paginableItemList.getTotalProducts() % pageSize == 0) {
            totalPages = (int)(paginableItemList.getTotalProducts() / pageSize);
        } else {
            totalPages = (int)(paginableItemList.getTotalProducts() / pageSize) + 1;
        }

        vm.setTotalPagingItems(totalPages);
        vm.setCurrentPage(pageNumber);

        model.addAttribute("vm",vm);
        return "/admin/tables/material";
    }

    @RequestMapping( value = "/material/search" , method = RequestMethod.GET)
    public String searchMaterial(@ModelAttribute MaterialName materialName , BindingResult erros , Model model){

        try {
            AdminMaterialVM vm = new AdminMaterialVM();
            vm.setMaterialList(materialService.findByNameContaining(materialName.getName()));
            vm.setKeyWord(materialName.getName());
            model.addAttribute("vm", vm);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/admin/tables/material-search";
    }

    @RequestMapping(path = "/product/image/{productId}")
    public String productImage(@PathVariable int productId, Model model){
        Product product = productService.findOne(productId);
        model.addAttribute("vm",product);
        return "/admin/tables/product-image";
    }



    @RequestMapping(path = "/category", method = RequestMethod.GET)
    public String category(Model model){
        List<ProductCategory> vm = productCategoryService.getListAllProductCategories();
        model.addAttribute("vm",vm);
        return "/admin/tables/category";
    }

    @RequestMapping(path = "/booked", method = RequestMethod.GET)
    public String booked(Model model){
        List<Booker> vm = bookerService.getListAllBookers();
        model.addAttribute("vm",vm);
        return "/admin/tables/booked";
    }

    @RequestMapping(path = "/material-history", method = RequestMethod.GET)
    public String materialHistory(Model model){
        List<MaterialHistory> vm = materialHistoryService.getListAllMaterialHistories();
        model.addAttribute("vm",vm);
        return "/admin/tables/material-history";
    }

    @GetMapping("/order-history")
    public String orderHistory(Model model,@RequestParam(value="pageNumber", required=false) Integer pageNumber){

        AdminOrderVM vm = new AdminOrderVM();

        int pageSize = Constant.DEFAULT_PAGE_SIZE;
        if(pageNumber == null) {
            pageNumber = 1;
        }
        PaginableItemList<Order> paginableItemList = orderService.getListOrders(Constant.DEFAULT_PAGE_SIZE,
                pageNumber - 1);

        vm.setOrderList(paginableItemList.getListData());

        int totalPages = 0;
        if(paginableItemList.getTotalProducts() % pageSize == 0) {
            totalPages = (int)(paginableItemList.getTotalProducts() / pageSize);
        } else {
            totalPages = (int)(paginableItemList.getTotalProducts() / pageSize) + 1;
        }

        vm.setTotalPagingItems(totalPages);
        vm.setCurrentPage(pageNumber);

        model.addAttribute("vm",vm);


        return "/admin/tables/order-history";
    }

    @GetMapping("/order-product/{orderId}")
    public String orderProduct(@PathVariable int orderId, Model model){
        Order order = orderService.findOne(orderId);
        model.addAttribute("vm", order);
        return "/admin/tables/order-product";
    }

    @GetMapping("/product-material/{productId}")
    public String productMaterial(@PathVariable int productId, Model model){
        Product product = productService.findOne(productId);
        model.addAttribute("vm", product);
        return "/admin/tables/product-material";
    }

    @GetMapping("/user")
    public String user(Model model){
        List<User> users = userService.getListAllUsers();
        model.addAttribute("vm", users);
        return "/admin/tables/user";
    }

    @GetMapping("/contact")
    public String contact(Model  model){
        List<Contact> contacts = contactService.getListAllContacts();
        model.addAttribute("vm",contacts);
        return "/admin/tables/contact";
    }


}
