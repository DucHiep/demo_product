package application.viewmodel.productdetail;

import application.data.model.Product;
import application.data.model.ProductCategory;

import java.util.ArrayList;
import java.util.List;

public class ProductSearchVM {
    private ArrayList<Product> products;
    private String keyWord;
    private List<ProductCategory> productCategoryList;

    public List<ProductCategory> getProductCategoryList() {
        return productCategoryList;
    }

    public void setProductCategoryList(List<ProductCategory> productCategoryList) {
        this.productCategoryList = productCategoryList;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }
}