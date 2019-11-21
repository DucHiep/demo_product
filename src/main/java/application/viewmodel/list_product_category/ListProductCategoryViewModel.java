package application.viewmodel.list_product_category;

import application.data.model.Product;
import application.data.model.ProductCategory;

import java.util.List;

public class ListProductCategoryViewModel {
    private ProductCategory productCategory;

    private List<Product> productList;

    private List<ProductCategory> productCategoryList;

    public List<ProductCategory> getProductCategoryList() {
        return productCategoryList;
    }

    public void setProductCategoryList(List<ProductCategory> productCategoryList) {
        this.productCategoryList = productCategoryList;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }
}
