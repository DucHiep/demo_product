package application.controller.api;

import application.data.model.ProductMaterial;
import application.data.service.MaterialService;
import application.data.service.ProductMaterialService;
import application.data.service.ProductService;
import application.model.api.BaseApiResult;
import application.model.api.DataApiResult;
import application.model.api.ProductMaterialDataModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/product-material")
public class ProductMaterialApiController {

    @Autowired
    private ProductMaterialService productMaterialService;

    @Autowired
    private MaterialService materialService;

    @Autowired
    private ProductService productService;

    @PostMapping("/create")
    public BaseApiResult createProductMaterial(@RequestBody ProductMaterialDataModel productMaterialDataModel){
        DataApiResult result = new DataApiResult();

        try {
            ProductMaterial productMaterial = new ProductMaterial();
            productMaterial.setMaterial(materialService.findOne(productMaterialDataModel.getMaterialId()));
            productMaterial.setProduct(productService.findOne(productMaterialDataModel.getProductId()));
            productMaterial.setName(productMaterialDataModel.getName());
            result.setSuccess(true);
            result.setMessage("Save product material successfully: " + productMaterial.getProductMaterialid());
            result.setData(productMaterial.getProductMaterialid());
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }
        return result;
    }

    @GetMapping("/detail/{productMaterialId}")
    public BaseApiResult detailProductMaterial(@PathVariable int productMaterialId){
        DataApiResult result= new DataApiResult();

        try {
            ProductMaterial productMaterial = productMaterialService.findOne(productMaterialId);
            if(productMaterial == null) {
                result.setSuccess(false);
                result.setMessage("Can't find this product material");
            } else {
                result.setSuccess(true);
                result.setData(productMaterial);
            }
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }

        return result;
    }

    @PostMapping("/update/{productMaterialId}")
    public BaseApiResult updateProductMaterial(@PathVariable int productMaterialId, @RequestBody ProductMaterialDataModel productMaterialDataModel){
        DataApiResult result = new DataApiResult();

        try {
            if(!productMaterialDataModel.getName().equals("")){
                ProductMaterial productMaterial = new ProductMaterial();
                productMaterial.setMaterial(materialService.findOne(productMaterialDataModel.getMaterialId()));
                productMaterial.setProduct(productService.findOne(productMaterialDataModel.getProductId()));
                productMaterial.setName(productMaterialDataModel.getName());
                result.setSuccess(true);
                result.setMessage("Update product material successfully: " + productMaterial.getProductMaterialid());
                result.setData(productMaterial.getProductMaterialid());
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

    @PostMapping("/delete/{productMaterialId}")
    public BaseApiResult deleteProductMaterial(@PathVariable int productMaterialId){
        BaseApiResult result = new BaseApiResult();
        try {
            if(productMaterialService.deleteProductMaterial(productMaterialId)){
                result.setSuccess(true);
                result.setMessage("Delete product material successfully");
            }
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }

        return result;
    }

}
