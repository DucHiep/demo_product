package application.extension;

import application.data.model.Product;

import java.util.Comparator;

public class ProductPriceComparatorIncrease implements Comparator<Product> {
    @Override
    public int compare(Product o1, Product o2) {
        if(o1.getPrice()==o2.getPrice()) return 0;
        if(o1.getPrice()>o2.getPrice()) return 1;
        return -1;
    }
}
