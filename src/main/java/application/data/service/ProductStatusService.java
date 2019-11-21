package application.data.service;


import application.data.model.PaginableItemList;
import application.data.model.ProductStatus;
import application.data.repository.ProductStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductStatusService {

    @Autowired
    private ProductStatusRepository productStatusRepository;

    @Transactional
    public void addNewListProductStatuses (List<ProductStatus> productStatusList) {
        productStatusRepository.save(productStatusList);
    }

    public void addNewProductStatus (ProductStatus productStatus){
        productStatusRepository.save(productStatus);
    }

    public ProductStatus findOne(int productStatusid) {
        return productStatusRepository.findOne(productStatusid);
    }

    public boolean deleteProductStatus(int productStatusid){
        try {
            productStatusRepository.delete(productStatusid);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateProductStatus(ProductStatus productStatus) {
        try {
            productStatusRepository.save(productStatus);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<ProductStatus> getListAllProductStatuses() {
        try {
            return productStatusRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public PaginableItemList<ProductStatus> getListProductStatuses(int pageSize, int pageNumber) {
        PaginableItemList<ProductStatus> paginableItemList = new PaginableItemList<>();
        paginableItemList.setPageSize(pageSize);
        paginableItemList.setPageNumber(pageNumber);

        Page<ProductStatus> pages = productStatusRepository.findAll(new PageRequest(pageNumber, pageSize));
        paginableItemList.setTotalProducts(pages.getTotalElements());
        paginableItemList.setListData(pages.getContent());
        return paginableItemList;
    }

}
