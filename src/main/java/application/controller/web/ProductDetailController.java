package application.controller.web;

import application.data.model.Product;
import application.data.service.ProductCategoryService;
import application.data.service.ProductService;
import application.viewmodel.productdetail.ProductDetailVM;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.HttpClientErrorException;

import java.util.*;

@Controller
@RequestMapping(path = "/product")
public class ProductDetailController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductCategoryService productCategoryService;

    @GetMapping("/{productId}")
    public String index(Model model, @PathVariable int productId){
        ProductDetailVM vm = new ProductDetailVM();



        Product existProduct = productService.findOne(productId);

        if(existProduct!=null){
            vm.setProduct(existProduct);
            List<Product> listProduct = existProduct.getProductCategory().getListProducts();
            Random random = new Random();


            Set<Product> products2 = new HashSet<>();

            for(int i=0;i<7;i++){
                products2.add(listProduct.get(random.nextInt(listProduct.size())));
            }

            vm.setProductList(products2);
            model.addAttribute("vm",vm);
            return "detail";
        }else {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
        }
    }
}
