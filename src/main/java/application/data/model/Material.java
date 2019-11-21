package application.data.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity(name = "tbl_material")
public class Material {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "materialid")
    @Id
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "image")
    private String image;

    @Column(name = "short_desc")
    private String shortDesc;

    @Column(name = "amount")
    private int amount;

    @Column(name = "price")
    private int price;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "material")
    private List<MaterialHistory> listMaterialHistories = new ArrayList<>();


    @ManyToMany(fetch = FetchType.LAZY,
                cascade = {
                    CascadeType.PERSIST,
                        CascadeType.MERGE
                })
    @JoinTable(name = "tbl_product_material",
                joinColumns = {@JoinColumn(name = "materialid")},
                inverseJoinColumns = {@JoinColumn(name = "productid")})
    private Set<Product> listProducts = new HashSet<>();


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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Set<Product> getListProducts() {
        return listProducts;
    }

    public void setListProducts(Set<Product> listProducts) {
        this.listProducts = listProducts;
    }

    public List<MaterialHistory> getListMaterialHistories() {
        return listMaterialHistories;
    }

    public void setListMaterialHistories(List<MaterialHistory> listMaterialHistories) {
        this.listMaterialHistories = listMaterialHistories;
    }
}
