package application.controller.api;

import application.data.model.ProductCategory;
import application.data.service.ProductCategoryService;
import application.model.api.BaseApiResult;
import application.model.api.DataApiResult;
import application.model.api.ProductCategoryDataModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product-category")
public class ProductCategoryApiController {

   @Autowired
   private ProductCategoryService productCategoryService;

   @PostMapping("/create")
   public BaseApiResult createProductCategory(@RequestBody ProductCategoryDataModel productCategoryDataModel){
      DataApiResult result = new DataApiResult();

      try {
         if(!productCategoryDataModel.getShortDesc().equals("")&&!productCategoryDataModel.getName().equals("")){
            ProductCategory categoryEntity = new ProductCategory();
            categoryEntity.setShortDesc(productCategoryDataModel.getShortDesc());
            categoryEntity.setName(productCategoryDataModel.getName());
//            categoryEntity.setCreatedDate(productCategoryDataModel.getCreatedDate());
            productCategoryService.addNewProductCategory(categoryEntity);
            result.setSuccess(true);
            result.setMessage("Save product category successfully: " + categoryEntity.getId());
            result.setData(categoryEntity.getId());
         }else {
            result.setSuccess(false);
            result.setMessage("Invalid model");
         }
      } catch (Exception e) {
         result.setSuccess(false);
         result.setMessage(e.getMessage());
         e.printStackTrace();
      }
      return result;
   }

   @GetMapping("/detail/{productCategoryId}")
   public BaseApiResult detailProductCategory(@PathVariable int productCategoryId){
      DataApiResult result= new DataApiResult();

      try {
         ProductCategory category = productCategoryService.findOne(productCategoryId);
         ProductCategoryDataModel productCategoryDataModel = new ProductCategoryDataModel();
         productCategoryDataModel.setName(category.getName());
         productCategoryDataModel.setId(category.getId());
         productCategoryDataModel.setShortDesc(category.getShortDesc());
         if(category == null) {
            result.setSuccess(false);
            result.setMessage("Can't find this product");
         } else {
            result.setSuccess(true);
            result.setData(productCategoryDataModel);
         }
      } catch (Exception e) {
         result.setSuccess(false);
         result.setMessage(e.getMessage());
      }

      return result;
   }

   @PostMapping("/update/{categoryId}")
   public  BaseApiResult updateProductCategory(@PathVariable int categoryId, @RequestBody ProductCategoryDataModel category){
      DataApiResult result = new DataApiResult();

      try {
         if(!category.getShortDesc().equals("")&&!category.getName().equals("")){
            ProductCategory categoryEntity = productCategoryService.findOne(categoryId);
            categoryEntity.setShortDesc(category.getShortDesc());
            categoryEntity.setName(category.getName());
//            categoryEntity.setCreatedDate(category.getCreatedDate());
            productCategoryService.addNewProductCategory(categoryEntity);
            result.setSuccess(true);
            result.setMessage("Update product category successfully: " + categoryEntity.getId());
            result.setData(categoryEntity.getId());
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

   @PostMapping("/delete/{productCategoryId}")
   public BaseApiResult deleteCategory(@PathVariable int productCategoryId){
      BaseApiResult result = new BaseApiResult();
      try {
         if(productCategoryService.deleteProductCategory(productCategoryId)){
            result.setSuccess(true);
            result.setMessage("Delete product category successfully");
         }
      } catch (Exception e) {
         result.setSuccess(false);
         result.setMessage(e.getMessage());
      }

      return result;
   }

}
