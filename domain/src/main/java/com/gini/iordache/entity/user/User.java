package com.gini.iordache.entity.user;

import com.gini.iordache.entity.order.ServiceOrder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.*;

@Getter
@Setter
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotNull(message = "required")
    @Size(min = 2,max= 20)
    @Column(name = "first_Name")
    private String firstName;

    @NotNull(message = "required")
    @Size(min = 2, message = "must have atleast 2 characters")
    @Column(name = "last_Name")
    private String lastName;

    @NotNull(message = "required")
    @Size(min = 2, message = "must have atlest 2 characterrs")
    @Column(name = "username")
    private String username;

                //todo: nu apare eroarea
    @Column(name = "email")
    @Email(message = "required")
    private String email;

                 //todo: nu apare eroarea
    @Column(name = "password")
    @NotBlank(message = "required")
    @Size(min = 3, message = "password must have at least 3 characters")
    private String password;

    private boolean isEnabled = false;
    private boolean isNonLoked = false;

    @ElementCollection(fetch = FetchType.LAZY)
    @Size( min = 1, message = "select at lest one")
    private Set<String> authorities = new HashSet<>();

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            fetch = FetchType.LAZY,
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
