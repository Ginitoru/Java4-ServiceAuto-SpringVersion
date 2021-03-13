package com.gini.iordache.entity.auto;

import com.gini.iordache.entity.labor.Labor;
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

    @ManyToOne
    private Vehicle vehicle;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @ManyToMany
    private List<Part> parts = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "service_order_part_count")
    private List<Integer> partCount = new ArrayList<>(); //retine nr de piese din comanda

    @ManyToMany
    private List<Labor> labors = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "service_order_labor_price")
    private List<Double> laborPrice = new ArrayList<>(); //retine pretul de pe fiecare manopera in comanda


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
