package com.gini.iordache.entity.auto;

import com.gini.iordache.entity.user.User;
import com.gini.iordache.utility.OrderStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class ServiceOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private double totalPrice;

    @OneToMany
    private List<CarProblems> carProblems = new ArrayList<>();

    @ManyToOne
    private User user;

    @ManyToMany
    private Set<Part> parts = new HashSet<>();

    @ManyToOne
    private Vehicle vehicle;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServiceOrder that = (ServiceOrder) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
