package application.data.service;


import application.data.model.PaginableItemList;
import application.data.model.Product;
import application.data.model.ProductCategory;
import application.data.repository.ProductCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductCategoryService {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Transactional
    public void addNewListProductCategories (List<ProductCategory> productCategories) {
        productCategoryRepository.save(productCategories);
    }

    public void addNewProductCategory (ProductCategory productCategory){
        productCategoryRepository.save(productCategory);
    }

    public ProductCategory findOne(int productcategoryid) {
        return productCategoryRepository.findOne(productcategoryid);
    }

    public boolean deleteProductCategory(int productcategoryid){
        try {
            productCategoryRepository.delete(productcategoryid);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateProductCategory(ProductCategory productCategory) {
        try {
            productCategoryRepository.save(productCategory);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<ProductCategory> getListAllProductCategories() {
        try {
            return productCategoryRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public PaginableItemList<ProductCategory> getListProductCategories(int pageSize, int pageNumber) {
        PaginableItemList<ProductCategory> paginableItemList = new PaginableItemList<>();
        paginableItemList.setPageSize(pageSize);
        paginableItemList.setPageNumber(pageNumber);

        Page<ProductCategory> pages = productCategoryRepository.findAll(new PageRequest(pageNumber, pageSize));
        paginableItemList.setTotalProducts(pages.getTotalElements());
        paginableItemList.setListData(pages.getContent());
        return paginableItemList;
    }

    public PaginableItemList<Product> getListProducts(int pageSize, int pageNumber, int categoryId) {
        PaginableItemList<Product> paginableItemList = new PaginableItemList<>();
        paginableItemList.setPageSize(pageSize);
        paginableItemList.setPageNumber(pageNumber);

        List<Product> products = productCategoryRepository.findOne(categoryId).getListProducts();
        System.out.println("Total products: " + products.size());
        Page<Product> pages = new PageImpl<Product>(products, new PageRequest(pageNumber,pageSize),products.size());
        System.out.println("Page: " + pages.getTotalElements());
        paginableItemList.setTotalProducts(pages.getTotalElements());
        paginableItemList.setListData(pages.getContent());
        return paginableItemList;
    }


}
