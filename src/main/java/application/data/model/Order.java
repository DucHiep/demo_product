package application.data.model;

import javax.persistence.*;
import java.util.*;

@Entity( name = "tbl_order")
public class Order {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "orderid")
    @Id
    private int id;


    @Column (name = "created_date")
    private Date createdDate;

    @Column(name = "address")
    private String address;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private int price;

    @Column(name = "user_point")
    private String userPoint;

    @Column(name = "phonenumber")
    private String phoneNumber;

    @Column(name = "guid")
    private String guid;

    @Column(name = "username")
    private String userName;

    @Column(name = "tableid")
    private int tableId;



    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "tbl_product_order",
            joinColumns = {@JoinColumn(name = "orderid")},
            inverseJoinColumns = {@JoinColumn(name = "productid")})
    private List<Product> listProducts = new ArrayList<>();



    @Column(name = "email")
    private String email;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
    private List<ProductOrder> listProductOrders = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ProductOrder> getListProductOrders() {
        return listProductOrders;
    }

    public void setListProductOrders(List<ProductOrder> listProductOrders) {
        this.listProductOrders = listProductOrders;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getUserPoint() {
        return userPoint;
    }

    public void setUserPoint(String userPoint) {
        this.userPoint = userPoint;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getTableId() {
        return tableId;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    public List<Product> getListProducts() {
        return listProducts;
    }

    public void setListProducts(List<Product> listProducts) {
        this.listProducts = listProducts;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
