package application.model.api;

import application.extension.CustomDateDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.Date;

public class UserDataModel {
    private String userName;
    private String passWord;
    private String avatar;
    @JsonDeserialize(using = CustomDateDeserializer.class)
    private Date createdDate;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
