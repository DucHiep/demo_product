package application.data.model;


import javax.persistence.*;
import java.util.Date;

@Entity(name = "tbl_product_history")
public class ProductHistory {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_historyid")
    @Id
    private int id;

    @Column(name = "price")
    private int price;

    @Column(name = "amount")
    private int amount;

    @Column(name = "created_date")
    private Date createdDate;

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "productid")
    private Product product;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
