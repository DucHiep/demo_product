package application.controller.api;

import application.data.model.Product;
import application.data.service.ProductCategoryService;
import application.data.service.ProductService;
import application.model.api.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("api/product")
public class ProductApiController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductCategoryService productCategoryService;


    @GetMapping("/getallproduct")
    public BaseApiResult getAllName() {
        DataApiResult result = new DataApiResult();
        ModelMapper modelMapper = new ModelMapper();
        try{
            ArrayList<Product> products  = productService.getAllPros();
            ArrayList<ProductSearchModel> productSearchModels = new ArrayList<>();
            for (Product p : products){
                ProductSearchModel productSearchModel = new ProductSearchModel();
                productSearchModel.setImage(p.mainImage());
                productSearchModel.setName(p.getName());
                productSearchModel.setPrice(p.getPrice());
                productSearchModels.add(productSearchModel);
            }
            result.setSuccess(true);
            result.setData(productSearchModels);
            result.setMessage("'success");
        } catch (Exception e) {
            result.setData(null);
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }
        return result;
    }


    @PostMapping(value = "/create")
    public BaseApiResult createProduct(@RequestBody ProductDataModel productDataModel){
        DataApiResult result = new DataApiResult();

        try {
            Product product = new Product();
            product.setName(productDataModel.getName());
            product.setShortDesc(productDataModel.getShortDesc());
            product.setPrice(productDataModel.getPrice());
            product.setProductCategory(productCategoryService.findOne(productDataModel.getCategoryId()));
            productService.addNewProduct(product);
            result.setData(product.getId());
            result.setMessage("Save product successfully: " + product.getId());
            result.setSuccess(true);
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }
        return result;
    }

    @GetMapping("/detail/{productId}")
    public BaseApiResult detailMaterial(@PathVariable int productId){
        DataApiResult result= new DataApiResult();

        try {
            Product product = productService.findOne(productId);
            if(product == null) {
                result.setSuccess(false);
                result.setMessage("Can't find this product");
            } else {
                ProductDataModel productDataModel = new ProductDataModel();
                productDataModel.setId(product.getId());
                productDataModel.setCategoryId(product.getProductCategory().getId());
                productDataModel.setName(product.getName());
                productDataModel.setPrice(product.getPrice());
                productDataModel.setShortDesc(product.getShortDesc());
                result.setSuccess(true);
                result.setData(productDataModel);
            }
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }

        return result;
    }

    @PostMapping("/update/{productId}")
    public BaseApiResult updateProduct(@PathVariable int productId,
                                       @RequestBody ProductDataModel productDataModel) {
        BaseApiResult result = new BaseApiResult();

        try {
            Product product = productService.findOne(productId);
            product.setName(productDataModel.getName());
            product.setShortDesc(productDataModel.getShortDesc());
            product.setPrice(productDataModel.getPrice());
            product.setProductCategory(productCategoryService.findOne(productDataModel.getCategoryId()));
            productService.addNewProduct(product);
            result.setSuccess(true);
            result.setMessage("Update product successfully");
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }

        return result;
    }


    @PostMapping("/delete/{productId}")
    public BaseApiResult deleteProduct(@PathVariable int productId){
        BaseApiResult result = new BaseApiResult();
        try {
            if(productService.deleteProduct(productId)){
                result.setSuccess(true);
                result.setMessage("Delete product successfully");
            }
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }

        return result;
    }


    @GetMapping("/custom")
    public BaseApiResult getCustomProduct(){
        DataApiResult result= new DataApiResult();

        try {
            result.setData(productService.listDemoCustomQueryProduct());
            result.setSuccess(true);
            result.setMessage("DM");
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }

        return result;
    }
}
