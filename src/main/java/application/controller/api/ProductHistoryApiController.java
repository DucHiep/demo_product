package application.controller.api;

import application.data.model.ProductHistory;
import application.data.service.ProductHistoryService;
import application.data.service.ProductService;
import application.model.api.BaseApiResult;
import application.model.api.DataApiResult;
import application.model.api.ProductHistoryDataModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/product-history")
public class ProductHistoryApiController {

    @Autowired
    private ProductHistoryService productHistoryService;

    @Autowired
    private ProductService productService;

    @PostMapping("/create")
    public BaseApiResult createProductHistory(@RequestBody ProductHistoryDataModel productHistoryDataModel){
        DataApiResult result = new DataApiResult();

        try {
            ProductHistory productHistory = new ProductHistory();
            productHistory.setAmount(productHistoryDataModel.getAmount());
            productHistory.setCreatedDate(productHistoryDataModel.getCreatedDate());
            productHistory.setPrice(productHistoryDataModel.getPrice());
            productHistory.setProduct(productService.findOne(productHistoryDataModel.getProductId()));
            productHistoryService.addNewProductHistory(productHistory);
            result.setData(productHistory.getId());
            result.setMessage("Save product history successfully: " + productHistory.getId());
            result.setSuccess(true);
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }
        return result;
    }

    @GetMapping("/detail/{productHistoryId}")
    public BaseApiResult detailProductHistory(@PathVariable int productHistoryId){
        DataApiResult result= new DataApiResult();

        try {
            ProductHistory productHistory = productHistoryService.findOne(productHistoryId);
            if(productHistory == null) {
                result.setSuccess(false);
                result.setMessage("Can't find this product history");
            } else {
                result.setSuccess(true);
                result.setData(productHistory);
            }
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }

        return result;
    }

    @PostMapping("/update/{productHistoryId}")
    public  BaseApiResult updateProductHistory(@PathVariable int productHistoryId, @RequestBody ProductHistoryDataModel productHistoryDataModel){
        DataApiResult result = new DataApiResult();

        try {
            ProductHistory productHistory = productHistoryService.findOne(productHistoryId);
            productHistory.setAmount(productHistoryDataModel.getAmount());
            productHistory.setCreatedDate(productHistoryDataModel.getCreatedDate());
            productHistory.setPrice(productHistoryDataModel.getPrice());
            productHistory.setProduct(productService.findOne(productHistoryDataModel.getProductId()));
            productHistoryService.addNewProductHistory(productHistory);
            result.setSuccess(true);
            result.setMessage("Update product image successfully: " + productHistory.getId());
            result.setData(productHistory);
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }
        return result;
    }

    @PostMapping("/delete/{productHistoryId}")
    public BaseApiResult deleteProductHistory(@PathVariable int productHistoryId){
        BaseApiResult result = new BaseApiResult();
        try {
            if(productHistoryService.deleteProductHistory(productHistoryId)){
                result.setSuccess(true);
                result.setMessage("Delete product history successfully");
            }
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }

        return result;
    }

}
