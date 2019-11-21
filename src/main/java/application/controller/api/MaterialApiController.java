package application.controller.api;


import application.data.model.Material;
import application.data.model.MaterialHistory;
import application.data.model.Product;
import application.data.service.MaterialHistoryService;
import application.data.service.MaterialService;
import application.model.api.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;

@RestController
@RequestMapping("api/material")
public class MaterialApiController {

    @Autowired
    private MaterialService materialService;

    @Autowired
    private MaterialHistoryService materialHistoryService;

    @GetMapping("/getallmaterial")
    public BaseApiResult getAllName() {
        DataApiResult result = new DataApiResult();
        try{
            ArrayList<Material> materials  = materialService.getAllMaterials();
            ArrayList<MaterialSearchModel> materialSearchModels = new ArrayList<>();
            for (Material m : materials){
                MaterialSearchModel materialSearchModel = new MaterialSearchModel();
                materialSearchModel.setName(m.getName());
                materialSearchModels.add(materialSearchModel);
            }
            result.setSuccess(true);
            result.setData(materialSearchModels);
            result.setMessage("'success");
        } catch (Exception e) {
            result.setData(null);
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }
        return result;
    }


    @PostMapping("/create")
    public BaseApiResult createMaterial(@RequestBody MaterialDataModel materialDataModel){
        DataApiResult result = new DataApiResult();

        try {
            Material material = new Material();
            if(materialDataModel.getAmount()>0){
                MaterialHistory materialHistory = new MaterialHistory();
                materialHistory.setMaterial(material);
                materialHistory.setAmount(materialDataModel.getAmount());
                materialHistory.setCreatedDate(new Date());
                materialHistoryService.addNewMaterialHistory(materialHistory);
            }else material.setAmount(0);
            material.setName(materialDataModel.getName());
            material.setPrice(materialDataModel.getPrice());
            materialService.addNewMaterial(material);
            result.setData(material.getId());
            result.setMessage("Save product material successfully: " + material.getId());
            result.setSuccess(true);
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }
        return result;
    }

    @GetMapping("/detail/{materialId}")
    public BaseApiResult detailMaterial(@PathVariable int materialId){
        DataApiResult result= new DataApiResult();

        try {
            Material material = materialService.findOne(materialId);
            MaterialDataModel materialDataModel = new MaterialDataModel();
            if(material == null) {
                result.setSuccess(false);
                result.setMessage("Can't find this material");
            } else {
                materialDataModel.setAmount(material.getAmount());
                materialDataModel.setId(material.getId());
                materialDataModel.setName(material.getName());
                materialDataModel.setPrice(material.getPrice());
                result.setSuccess(true);
                result.setData(materialDataModel);
            }
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }

        return result;
    }

    @PostMapping("/update/{materialId}")
    public BaseApiResult updateMaterial(@PathVariable int materialId, @RequestBody MaterialDataModel materialDataModel){
        BaseApiResult result = new BaseApiResult();

        try {
            Material material = materialService.findOne(materialId);
            material.setAmount(materialDataModel.getAmount()+material.getAmount());
            if(materialDataModel.getAmount()>0){
                MaterialHistory materialHistory = new MaterialHistory();
                materialHistory.setMaterial(material);
                materialHistory.setAmount(materialDataModel.getAmount());
                materialHistory.setCreatedDate(new Date());
                materialHistoryService.addNewMaterialHistory(materialHistory);
            }
            material.setName(materialDataModel.getName());
            material.setPrice(materialDataModel.getPrice());
            materialService.addNewMaterial(material);
            result.setSuccess(true);
            result.setMessage("Update material successfully: " + material.getId());
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }
        return result;
    }


//    @PostMapping("/delete/{materialId}")
//    public BaseApiResult deleteMaterial(@PathVariable int materialId){
//        BaseApiResult result = new BaseApiResult();
//        try {
//            if(materialService.deteleMaterial(materialId)){
//                result.setSuccess(true);
//                result.setMessage("Delete material successfully");
//            }
//        } catch (Exception e) {
//            result.setSuccess(false);
//            result.setMessage(e.getMessage());
//        }
//
//        return result;
//    }

}
