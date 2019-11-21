package application.data.service;

import application.data.model.Material;
import application.data.model.PaginableItemList;
import application.data.model.Product;
import application.data.repository.MaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class MaterialService {

    @Autowired
    private MaterialRepository materialRepository;

    public void addNewMaterial (Material material){
        materialRepository.save(material);
    }

    @Transactional
    public void addNewListMaterials (List<Material> materials){
        materialRepository.save(materials);
    }

    public Material findOne(int materialid) {
        return materialRepository.findOne(materialid);
    }

    public boolean updateMaterial (Material material) {
        try {
            materialRepository.save(material);
            return true;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deteleMaterial (int materialid) {
        try {
            materialRepository.delete(materialid);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public PaginableItemList<Material> getListPageMaterials(int pageSize, int pageNumber) {
        PaginableItemList<Material> paginableItemList = new PaginableItemList<>();
        paginableItemList.setPageSize(pageSize);
        paginableItemList.setPageNumber(pageNumber);

        Page<Material> pages = materialRepository.findAll(new PageRequest(pageNumber, pageSize));
        paginableItemList.setTotalProducts(pages.getTotalElements());
        paginableItemList.setListData(pages.getContent());
        return paginableItemList;
    }

    public List<Material> getListAllMaterials() {
        try {
            return materialRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    //    =====them moi======
    public Material findById(int id){
        return materialRepository.findById(id);
    }

    public Object findByName(String username){
        return materialRepository.findByName(username);
    }

    public ArrayList<Material> getAllMaterials(){
        return materialRepository.getAllMaterials();
    }

    public ArrayList<Material> findByNameContaining(String username) {
        return materialRepository.findByNameContaining(username);
    }
}
