package application.controller.web;


import application.data.model.Material;
import application.data.service.MaterialService;
import application.model.api.MaterialName;
import application.model.api.MaterialSearchModel;
import application.viewmodel.material.MaterialSearchVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.naming.Binding;
import java.util.ArrayList;

@Controller
@RequestMapping(path = "/material")
public class MaterialController {
    @Autowired
    private MaterialService materialService;

    @RequestMapping( value = "/search" , method = RequestMethod.GET)
    public String searchMaterial(@ModelAttribute MaterialName materialName , BindingResult erros , Model model){

        try {
            MaterialSearchVM materialSearchVM =new MaterialSearchVM();
            ArrayList<Material> materials =new ArrayList<>();
            materials = materialService.findByNameContaining(materialName.getName());
            materialSearchVM.setMaterials(materials);
            materialSearchVM.setKeyWord(materialName.getName());

            model.addAttribute("vm", materialSearchVM);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/test";
    }

    @GetMapping("/test")
    public String index(Model model) {
//        List<Role> listRoles = userService.getListRole();
//        model.addAttribute("listRoles", listRoles);
        return "/materialsearchdemo";
    }
}
