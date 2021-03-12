package com.gini.iordache.entity.auto;

import com.gini.iordache.entity.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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



}
