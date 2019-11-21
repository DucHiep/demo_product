package application.data.service;


import application.data.model.PaginableItemList;
import application.data.model.ProductOrder;
import application.data.repository.ProductOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductOrderService {
    @Autowired
    private ProductOrderRepository productOrderRepository;

    public void addNewProductOrder(ProductOrder productOrder) {
        productOrderRepository.save(productOrder);
    }

    @Transactional
    public void addNewListProductOrders(List<ProductOrder> listProductOrders) {
        productOrderRepository.save(listProductOrders);
    }


    public ProductOrder findOne(int productOrderId) {
        return productOrderRepository.findOne(productOrderId);
    }


    public boolean updateProductOrder(ProductOrder productOrder) {
        try {
            productOrderRepository.save(productOrder);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteProductOrder(int productOrderId) {
        try {
            productOrderRepository.delete(productOrderId);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    public PaginableItemList<ProductOrder> getListProductOrders(int pageSize, int pageNumber) {
        PaginableItemList<ProductOrder> paginableItemList = new PaginableItemList<>();
        paginableItemList.setPageSize(pageSize);
        paginableItemList.setPageNumber(pageNumber);

        Page<ProductOrder> pages = productOrderRepository.findAll(new PageRequest(pageNumber, pageSize));
        paginableItemList.setTotalProducts(pages.getTotalElements());
        paginableItemList.setListData(pages.getContent());
        return paginableItemList;
    }

    public List<ProductOrder> getListAllProductOrders() {
        try {
            return productOrderRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
