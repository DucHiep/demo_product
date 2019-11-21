package application.controller.api;

import application.data.model.MaterialHistory;
import application.data.service.MaterialHistoryService;
import application.data.service.MaterialService;
import application.model.api.BaseApiResult;
import application.model.api.DataApiResult;
import application.model.api.MaterialHistoryDataModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping(path = "api/material-history")
public class MaterialHistoryApiController {

    @Autowired
    private MaterialHistoryService materialHistoryService;

    @Autowired
    private MaterialService materialService;


    @PostMapping("/create")
    public BaseApiResult createMaterialHistory(@RequestBody MaterialHistoryDataModel materialHistoryDataModel){
        DataApiResult result = new DataApiResult();

        try {
            MaterialHistory materialHistory = new MaterialHistory();
            materialHistory.setAmount(materialHistoryDataModel.getAmount());
            materialHistory.setCreatedDate(new Date());
            materialHistory.setMaterial(materialService.findOne(materialHistoryDataModel.getMaterialId()));
            materialHistoryService.addNewMaterialHistory(materialHistory);
            result.setData(materialHistory.getId());
            result.setMessage("Save material history successfully: " + materialHistory.getId());
            result.setSuccess(true);
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }
        return result;
    }

}
