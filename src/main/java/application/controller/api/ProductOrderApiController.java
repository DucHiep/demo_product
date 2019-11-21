package application.controller.api;

import application.data.model.ProductOrder;
import application.data.service.OrderService;
import application.data.service.ProductOrderService;
import application.data.service.ProductService;
import application.model.api.BaseApiResult;
import application.model.api.DataApiResult;
import application.model.api.ProductOrderDataModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/product-order")
public class ProductOrderApiController {

    @Autowired
    private ProductOrderService productOrderService;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;


    @PostMapping("/create")
    public BaseApiResult createProductOrder(@RequestBody ProductOrderDataModel productOrderDataModel){
        DataApiResult result = new DataApiResult();

        try {
            if(productOrderDataModel.getOrderid()!=0&&productOrderDataModel.getAmount()!=0&&productOrderDataModel.getProductid()!=0){
                ProductOrder productOrder = new ProductOrder();
                productOrder.setOrder(orderService.findOne(productOrderDataModel.getOrderid()));
                productOrder.setAmount(productOrderDataModel.getAmount());
                productOrder.setProduct(productService.findOne(productOrderDataModel.getProductid()));
                productOrderService.addNewProductOrder(productOrder);
                result.setSuccess(true);
                result.setMessage("Save product order successfully: " + productOrder.getId());
                result.setData(productOrder.getId());
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

    @GetMapping("/detail/{productOrderId}")
    public BaseApiResult detailProductOrder(@PathVariable int productOrderId){
        DataApiResult result= new DataApiResult();

        try {
            ProductOrder productOrder = productOrderService.findOne(productOrderId);
            if(productOrder == null) {
                result.setSuccess(false);
                result.setMessage("Can't find this product order");
            } else {
                result.setSuccess(true);
                result.setData(productOrder);
            }
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }

        return result;
    }

    @PostMapping("/update/{productOrderId}")
    public  BaseApiResult updateProductOrder(@PathVariable int productOrderId, @RequestBody ProductOrderDataModel productOrderDataModel){
        DataApiResult result = new DataApiResult();

        try {
            if(productOrderDataModel.getOrderid()!=0&&productOrderDataModel.getAmount()!=0&&productOrderDataModel.getProductid()!=0){
                ProductOrder productOrder = new ProductOrder();
//                productOrder.setOrder(orderService.findOne(productOrderDataModel.getOrderid()));
                productOrder.setAmount(productOrderDataModel.getAmount());
                productOrder.setProduct(productService.findOne(productOrderDataModel.getProductid()));

                productOrderService.addNewProductOrder(productOrder);
                result.setSuccess(true);
                result.setMessage("Save product order successfully: " + productOrder.getId());
                result.setData(productOrder.getId());
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


    @PostMapping("/delete/{productOrderId}")
    public BaseApiResult deleteProductImage(@PathVariable int productOrderId){
        BaseApiResult result = new BaseApiResult();
        try {
            if(productOrderService.deleteProductOrder(productOrderId)){
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
