package org.launchcode.prospector6.models;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="roles")
public class Role {

    @Id
    @GeneratedValue
    private int id;

    private String role;

    @OneToMany(cascade=CascadeType.ALL)
    @JoinTable(name="user_roles",
            joinColumns={@JoinColumn(name="role_id", referencedColumnName="id")},
            inverseJoinColumns={@JoinColumn(name="user_id", referencedColumnName="id")})
    private List<User> userList;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public List<User> getUserList() {
        return userList;
        }

        public void setUserList(List<User> userList) {
        this.userList = userList;
        }


}
