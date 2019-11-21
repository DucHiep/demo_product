package application.data.service;

import application.data.model.PaginableItemList;
import application.data.model.ProductMaterial;
import application.data.repository.ProductMaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductMaterialService {

    @Autowired
    private ProductMaterialRepository productMaterialRepository;

    public void addNewProductMaterial(ProductMaterial productMaterial) {
        productMaterialRepository.save(productMaterial);
    }

    @Transactional
    public void addNewListProductMaterials(List<ProductMaterial> listProductMaterials) {
        productMaterialRepository.save(listProductMaterials);
    }


    public ProductMaterial findOne(int productMaterialId) {
        return productMaterialRepository.findOne(productMaterialId);
    }


    public boolean updateProductMaterial(ProductMaterial productMaterial) {
        try {
            productMaterialRepository.save(productMaterial);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteProductMaterial(int productMaterialId) {
        try {
            productMaterialRepository.delete(productMaterialId);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    public PaginableItemList<ProductMaterial> getListProductMaterials(int pageSize, int pageNumber) {
        PaginableItemList<ProductMaterial> paginableItemList = new PaginableItemList<>();
        paginableItemList.setPageSize(pageSize);
        paginableItemList.setPageNumber(pageNumber);

        Page<ProductMaterial> pages = productMaterialRepository.findAll(new PageRequest(pageNumber, pageSize));
        paginableItemList.setTotalProducts(pages.getTotalElements());
        paginableItemList.setListData(pages.getContent());
        return paginableItemList;
    }

    public List<ProductMaterial> getListAllProductMaterials() {
        try {
            return productMaterialRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

}
