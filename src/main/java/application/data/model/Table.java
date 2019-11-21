package application.data.model;

import application.data.service.CartProductService;
import application.data.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity ( name ="tbl_table")
public class Table {



    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "tableid")
    @Id
    private int id;


    @Column(name = "name")
    private String name;


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

}
