package application.viewmodel.productdetail;


import application.data.model.Product;

import java.util.List;
import java.util.Set;

public class ProductDetailVM {
    private Product product;

    private Set<Product> productList;


    public Set<Product> getProductList() {
        return productList;
    }

    public void setProductList(Set<Product> productList) {
        this.productList = productList;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
