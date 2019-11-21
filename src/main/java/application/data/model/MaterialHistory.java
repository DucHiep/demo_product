package application.data.model;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "tbl_material_history")
public class MaterialHistory {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "material_historyid")
    @Id
    private int id;

    @Column(name = "amount")
    private int amount;

    @Column(name = "created_date")
    private Date createdDate;

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "materialid")

    private Material material;

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

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }
}
