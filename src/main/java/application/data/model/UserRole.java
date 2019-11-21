package application.data.model;

import javax.persistence.*;

@Entity(name = "tbl_user_role")
public class UserRole {

    @GeneratedValue(strategy= GenerationType.AUTO)
    @Id
    @Column(name = "user_roleid")
    private int id;

    @Column(name = "userid")
    private int userId;

    @Column(name = "roleid")
    private int roleId;

    @Column(name = "status")
    private int status;

    public UserRole(int userId, int roleId, int status) {
        this.userId = userId;
        this.roleId = roleId;
        this.status = status;
    }

    public UserRole() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
