package application.data.model;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "tbl_booker")
public class Booker {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "bookerid")
    @Id
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "table_amount")
    private int tableAmount;

    @Column(name = "time_booked")
    private Date timeBooked;

    @Column(name = "comment")
    private String comment;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getTimeBooked() {
        return timeBooked;
    }

    public void setTimeBooked(Date timeBooked) {
        this.timeBooked = timeBooked;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTableAmount() {
        return tableAmount;
    }

    public void setTableAmount(int tableAmount) {
        this.tableAmount = tableAmount;
    }
}
