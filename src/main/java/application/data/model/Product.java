package application.data.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.*;

@Entity(name = "tbl_product")
public class Product {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "productid")
    @Id
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "short_desc")
    private String shortDesc;

    @Column(name = "point")
    private int point;

    @Column(name = "price")
    private int price;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "categoryid", insertable = false, updatable = false)
    private int categoryid;

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "categoryid")

    private ProductCategory productCategory;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    private List<ProductImage> listProductImages = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    private Set<ProductStatus> listProductStatus;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
        private Set<ProductHistory> listProductHistories;


    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "tbl_product_material",
            joinColumns = {@JoinColumn(name = "productid")},
            inverseJoinColumns = {@JoinColumn(name = "materialid")})
    private List<Material> listMaterials = new ArrayList<>();


    public String mainImage(){
        String main = "";
        main = this.listProductImages.get(0).getImage();
        return main;
    }

    public int amount(){
        List<Material> materials = this.getListMaterials();
        int min=100000;
        for(Material material : materials){
            if(material.getAmount()<min)    min=material.getAmount();
        }
        return min;
    }


    public List<Material> getListMaterials() {
        return listMaterials;
    }

    public void setListMaterials(List<Material> listMaterials) {
        this.listMaterials = listMaterials;
    }

    public Set<ProductHistory> getListProductHistories() {
        return listProductHistories;
    }

    public void setListProductHistories(Set<ProductHistory> listProductHistories) {
        this.listProductHistories = listProductHistories;
    }

    public Set<ProductStatus> getListProductStatus() {
        return listProductStatus;
    }

    public void setListProductStatus(Set<ProductStatus> listProductStatus) {
        this.listProductStatus = listProductStatus;
    }

    public List<ProductImage> getListProductImages() {
        return listProductImages;
    }

    public void setListProductImages(List<ProductImage> listProductImages) {
        this.listProductImages = listProductImages;
    }

    public int getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(int categoryid) {
        this.categoryid = categoryid;
    }

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

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }
}
