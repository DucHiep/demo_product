package application.data.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "tbl_role")
public class Role {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "roleid")
    @Id
    private int Id;

    @Column(name = "name")
    private String name;

    @Column(name = "short_desc")
    private String shortDesc;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "tbl_user_role",
            joinColumns = {@JoinColumn(name = "roleid")},
            inverseJoinColumns = {@JoinColumn(name = "userid")})
    private Set<User> listRole = new HashSet<>();


    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
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

    public Set<User> getListRole() {
        return listRole;
    }

    public void setListRole(Set<User> listRole) {
        this.listRole = listRole;
    }
}
