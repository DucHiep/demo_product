package application.viewmodel.admin;

import application.data.model.Product;
import application.data.model.ProductCategory;

import java.util.List;

public class AdminProductVM {
    private String keyWord;
    private int currentPage;
    private int totalPagingItems;
    private List<Product> productList;
    private List<ProductCategory> productCategoryList;


    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public List<ProductCategory> getProductCategoryList() {
        return productCategoryList;
    }

    public void setProductCategoryList(List<ProductCategory> productCategoryList) {
        this.productCategoryList = productCategoryList;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPagingItems() {
        return totalPagingItems;
    }

    public void setTotalPagingItems(int totalPagingItems) {
        this.totalPagingItems = totalPagingItems;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}
