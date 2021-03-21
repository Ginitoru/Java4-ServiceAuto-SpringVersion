package com.gini.iordache.entity.user;

import com.gini.iordache.entity.order.ServiceOrder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

@Getter
@Setter
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "first_Name")
    private String firstName;

    @Column(name = "last_Name")
    private String lastName;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    private boolean isEnabled = false;
    private boolean isNonLoked = false;

    @ElementCollection
    private Set<String> authorities = new HashSet<>();

    @OneToOne(cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY, // lasa Lazy ca altfel imi face 3 selecuri cand ma loghez:D
            mappedBy = "user")
    private ActivationToken activationToken;

    @OneToMany(mappedBy = "user")
    private Set<ServiceOrder> serviceOrders = new HashSet<>();


    public User() {
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(username, user.username) || Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, email);
    }
}
