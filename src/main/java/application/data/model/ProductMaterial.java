package application.data.model;

import javax.persistence.*;

@Entity(name = "tbl_product_material")
public class ProductMaterial {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_materialid")
    @Id
    private int productMaterialid;

    @Column(name = "name")
    private String name;

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "materialid")
    private Material material;

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "productid")
    private Product product;

    public int getProductMaterialid() {
        return productMaterialid;
    }

    public void setProductMaterialid(int productMaterialid) {
        this.productMaterialid = productMaterialid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
