package application.data.model;

import javax.persistence.*;

@Entity(name = "tbl_product_order")
public class ProductOrder {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_orderid")
    @Id
    private int id;

    @Column(name = "amount")
    private int amount;

    public int price(){
        int result = this.product.getPrice()*this.amount;
        return result;
    }

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "orderid")
    private Order order;

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "productid")
    private Product product;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

}
