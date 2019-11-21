package application.controller.api;


import application.data.model.ProductImage;
import application.data.service.ProductImageService;
import application.data.service.ProductService;
import application.model.api.BaseApiResult;
import application.model.api.DataApiResult;
import application.model.api.ProductImageDataModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product-image")
public class ProductImageApiController {

    @Autowired
    private ProductImageService productImageService;

    @Autowired
    private ProductService productService;

    @PostMapping("/create")
    public BaseApiResult createProductImage(@RequestBody ProductImageDataModel productImageDataModel){
        DataApiResult result = new DataApiResult();

        try {
            if(!productImageDataModel.getImage().equals("")){
                ProductImage productImage = new ProductImage();
                productImage.setImage(productImageDataModel.getImage());
                productImage.setProduct(productService.findOne(productImageDataModel.getProductid()));
                productImageService.addNewProductImage(productImage);
                result.setSuccess(true);
                result.setMessage("Save product image successfully: " + productImage.getId());
                result.setData(productImage.getId());
            }else {
                result.setSuccess(false);
                result.setMessage("Invalid model");
            }
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }
        return result;
    }

    @GetMapping("/detail/{productImageId}")
    public BaseApiResult detailProductImage(@PathVariable int productImageId){
        DataApiResult result= new DataApiResult();

        try {
            ProductImage productImage = productImageService.findOne(productImageId);
            if(productImage == null) {
                result.setSuccess(false);
                result.setMessage("Can't find this product image");
            } else {
                result.setSuccess(true);
                result.setData(productImage);
            }
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }

        return result;
    }


    @PostMapping("/update/{productImageId}")
    public BaseApiResult updateProductImage(@PathVariable int productImageId, @RequestBody ProductImageDataModel productImageDataModel){
        DataApiResult result = new DataApiResult();

        try {
            if(!productImageDataModel.getImage().equals("")){
                ProductImage productImage = productImageService.findOne(productImageId);
                productImage.setProduct(productService.findOne(productImageDataModel.getProductid()));
                productImage.setImage(productImageDataModel.getImage());
                productImageService.addNewProductImage(productImage);
                result.setSuccess(true);
                result.setMessage("Update product image successfully: " + productImage.getId());
                result.setData(productImage.getId());
            }else {
                result.setSuccess(false);
                result.setMessage("Invalid model");
            }
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }
        return result;
    }

    @PostMapping("/delete/{productImageId}")
    public BaseApiResult deleteProductImage(@PathVariable int productImageId){
        BaseApiResult result = new BaseApiResult();
        try {
            if(productImageService.deleteProductImage(productImageId)){
                result.setSuccess(true);
                result.setMessage("Delete product image successfully");
            }
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }

        return result;
    }

}
