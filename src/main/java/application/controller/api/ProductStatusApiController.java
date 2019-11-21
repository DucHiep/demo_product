package application.controller.api;


import application.data.model.ProductStatus;
import application.data.service.ProductService;
import application.data.service.ProductStatusService;
import application.model.api.BaseApiResult;
import application.model.api.DataApiResult;
import application.model.api.ProductStatusDataModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/product-status")
public class ProductStatusApiController {

    @Autowired
    private ProductStatusService productStatusService;

    @Autowired
    private ProductService productService;

    @PostMapping("/create")
    public BaseApiResult createProductStatus(@RequestBody ProductStatusDataModel productStatusDataModel){
        DataApiResult result = new DataApiResult();

        try {
            if(!productStatusDataModel.getName().equals("")&&!productStatusDataModel.getShortDesc().equals("")){
                ProductStatus productStatus = new ProductStatus();
                productStatus.setName(productStatusDataModel.getName());
                productStatus.setShortDesc(productStatusDataModel.getShortDesc());
                productStatus.setProduct(productService.findOne(productStatusDataModel.getProductId()));
                productStatusService.addNewProductStatus(productStatus);
                result.setSuccess(true);
                result.setMessage("Save product status successfully: " + productStatus.getId());
                result.setData(productStatus.getId());
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

    @GetMapping("/detail/{productStatusId}")
    public BaseApiResult detailProductStatus(@PathVariable int productStatusId){
        DataApiResult result= new DataApiResult();

        try {
            ProductStatus productStatus = productStatusService.findOne(productStatusId);
            if(productStatus == null) {
                result.setSuccess(false);
                result.setMessage("Can't find this product status");
            } else {
                result.setSuccess(true);
                result.setData(productStatus);
            }
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }

        return result;
    }

    @PostMapping("/update/{productStatusId}")
    public  BaseApiResult updateProductImage(@PathVariable int productStatusId, @RequestBody ProductStatusDataModel productStatusDataModel){
        DataApiResult result = new DataApiResult();

        try {
            if(!productStatusDataModel.getName().equals("")&&!productStatusDataModel.getShortDesc().equals("")){
                ProductStatus productStatus = productStatusService.findOne(productStatusId);
                productStatus.setProduct(productService.findOne(productStatusDataModel.getProductId()));
                productStatus.setName(productStatusDataModel.getName());
                productStatus.setShortDesc(productStatusDataModel.getShortDesc());
                productStatusService.addNewProductStatus(productStatus);
                result.setSuccess(true);
                result.setMessage("Update product status successfully: " + productStatus.getId());
                result.setData(productStatus.getId());
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


    @PostMapping("/delete/{productStatusId}")
    public BaseApiResult deleteProductImage(@PathVariable int productStatusId){
        BaseApiResult result = new BaseApiResult();
        try {
            if(productStatusService.deleteProductStatus(productStatusId)){
                result.setSuccess(true);
                result.setMessage("Delete product status successfully");
            }
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }

        return result;
    }

}
