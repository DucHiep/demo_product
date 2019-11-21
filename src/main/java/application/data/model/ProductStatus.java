package application.data.model;

import javax.persistence.*;

@Entity(name = "tbl_product_status")
public class ProductStatus {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_statusid")
    @Id
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "short_desc")
    private String shortDesc;

    @ManyToOne(optional = true,fetch = FetchType.LAZY)
    @JoinColumn(name = "productid")
    private Product product;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
