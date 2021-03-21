package com.gini.iordache.entity.clients;

import com.gini.iordache.entity.order.ServiceOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Embedded
    private Adress adress;

    @OneToMany(mappedBy = "client")
    private Set<ServiceOrder> serviceOrders = new HashSet<>();







}
