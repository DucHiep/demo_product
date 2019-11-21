package application.controller.web;


import application.data.model.Product;
import application.data.service.ProductCategoryService;
import application.data.service.ProductService;
import application.model.api.ProductName;
import application.viewmodel.productdetail.ProductSearchVM;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;

@Controller
@RequestMapping(path="/product")
public class ProductController {


    @Autowired
    private ProductService productService;

    @Autowired
    private ProductCategoryService productCategoryService;

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String searchProduct(@ModelAttribute ProductName productName, BindingResult errors, Model model) {
        ModelMapper modelMapper = new ModelMapper();
        ProductSearchVM productSearchVM = new ProductSearchVM();
        try {
            ArrayList<Product> products =new ArrayList<>();
            products =  productService.findByNameContaining(productName.getName());
            productSearchVM.setProducts(products);
            productSearchVM.setKeyWord(productName.getName());
            productSearchVM.setProductCategoryList(productCategoryService.getListAllProductCategories());
            model.addAttribute("vm",productSearchVM);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/menu-search";
    }
}
