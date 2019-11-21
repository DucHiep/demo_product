package application.controller.web;


import application.constant.Constant;
import application.data.model.*;
import application.data.service.*;
import application.model.api.ProductName;
import application.viewmodel.table.CartTableViewModel;
import application.viewmodel.table.ListProductTableViewModel;
import application.viewmodel.table.TableViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(path = "/admin/table")
public class TableController {

    @Autowired
    private TableService tableService;

    @Autowired
    private ProductService  productService;

    @Autowired
    private CartService cartService;

    @Autowired
    private CartProductService cartProductService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductOrderService productOrderService;

    @Autowired
    private MaterialService materialService;

    @GetMapping("")
    public String tableUI(Model model){
        List<Table> tableList = tableService.getListAllTables();
        List<TableViewModel> vm = new ArrayList<>();
        for(Table table : tableList){
            Cart cart = cartService.findByTableId(table.getId()).get(0);
            TableViewModel tableViewModel = new TableViewModel();
            tableViewModel.setName(table.getName());
            tableViewModel.setId(table.getId());
            if(cart.getListCartProducts().size()>0) tableViewModel.setStatus(1);
            else tableViewModel.setStatus(0);
            vm.add(tableViewModel);
        }
        model.addAttribute("vm",vm);
        return "/admin/pick-table/picktable";
    }


    @GetMapping("/list-product/{tableId}")
    public String tableListProduct(Model model, @PathVariable int tableId,
                                   @RequestParam(value="pageNumber", required=false) Integer pageNumber){
        ListProductTableViewModel vm = new ListProductTableViewModel();

        Cart cart = cartService.findByTableId(tableId).get(0);

        Table table = tableService.findOne(tableId);

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
        vm.setCartId(cart.getId());
        vm.setTableId(tableId);
        vm.setKeyWord(table.getName());
        model.addAttribute("vm",vm);
        return "/admin/pick-table/list-product";
    }


    @RequestMapping(path = "/list-product/search/{tableId}", method = RequestMethod.GET)
    public String searchProductList(@ModelAttribute ProductName productName, BindingResult errors, Model model,
                                    @PathVariable int tableId) {
        try {
            ListProductTableViewModel vm = new ListProductTableViewModel();
            Cart cart = cartService.findByTableId(tableId).get(0);
            vm.setCartId(cart.getId());
            vm.setProductList(productService.findByNameContaining(productName.getName()));
            vm.setKeyWord(productName.getName());
            vm.setTableId(tableId);
            model.addAttribute("vm",vm);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/admin/pick-table/list-product-search";
    }


    @GetMapping("/cart/{tableId}")
    public String tableCart(@PathVariable int tableId, Model model){
        CartTableViewModel vm = new CartTableViewModel();
        Cart cart = cartService.findByTableId(tableId).get(0);
        int sum=0;

        for(CartProduct cartProduct : cart.getListCartProducts()){
            sum+=cartProduct.price();
        }

        cart.setPrice(sum);

        vm.setCart(cart);
        vm.setTableId(tableId);
        model.addAttribute("vm",vm);
        return "/admin/pick-table/cart";
    }

    @RequestMapping(path = "/cart/{tableId}", method = RequestMethod.POST)
    public String tableOrderSave(@PathVariable int tableId){
        Order order = new Order();
        order.setTableId(tableId);

        Cart cart = cartService.findByTableId(tableId).get(0);


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
//            cartProductService.deteleCartProduct(cartProduct.getId());
        }
        order.setPrice(sum);
        order.setCreatedDate(new Date());

        orderService.addNewOrder(order);

        productOrderService.addNewListProductOrders(productOrders);

        cartService.deteleCart(cart.getId());
        Cart cart1 = new Cart();
        cart1.setTableId(tableId);
        cartService.addNewCart(cart1);

        return "redirect:/admin/table";
    }


}
