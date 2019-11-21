package application.data.service;

import application.data.model.PaginableItemList;
import application.data.model.ProductHistory;
import application.data.repository.ProductHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductHistoryService {

    @Autowired
    private ProductHistoryRepository productHistoryRepository;

    @Transactional
    public void addNewListProductHistories (List<ProductHistory> productHistories) {
        productHistoryRepository.save(productHistories);
    }

    public void addNewProductHistory (ProductHistory producHistory){
        productHistoryRepository.save(producHistory);
    }

    public ProductHistory findOne(int productHistoryid) {
        return productHistoryRepository.findOne(productHistoryid);
    }

    public boolean deleteProductHistory(int productHistoryid){
        try {
            productHistoryRepository.delete(productHistoryid);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateProductHistory(ProductHistory productHistory) {
        try {
            productHistoryRepository.save(productHistory);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<ProductHistory> getListAllProductHistories() {
        try {
            return productHistoryRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public PaginableItemList<ProductHistory> getListProductHistories(int pageSize, int pageNumber) {
        PaginableItemList<ProductHistory> paginableItemList = new PaginableItemList<>();
        paginableItemList.setPageSize(pageSize);
        paginableItemList.setPageNumber(pageNumber);

        Page<ProductHistory> pages = productHistoryRepository.findAll(new PageRequest(pageNumber, pageSize));
        paginableItemList.setTotalProducts(pages.getTotalElements());
        paginableItemList.setListData(pages.getContent());
        return paginableItemList;
    }

}
