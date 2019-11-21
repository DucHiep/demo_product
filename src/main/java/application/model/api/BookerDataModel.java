package application.model.api;

import application.extension.CustomDateDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.beans.propertyeditors.CustomDateEditor;

import java.util.Date;

public class BookerDataModel {
    private int id;
    private String name;
    private String phone;
    private String email;
    private String comment;
    private int tableAmount;
    @JsonDeserialize(using = CustomDateDeserializer.class)
    private Date timeBooked;

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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getTableAmount() {
        return tableAmount;
    }

    public void setTableAmount(int tableAmount) {
        this.tableAmount = tableAmount;
    }

    public Date getTimeBooked() {
        return timeBooked;
    }

    public void setTimeBooked(Date timeBooked) {
        this.timeBooked = timeBooked;
    }
}
