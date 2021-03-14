package com.gini.iordache.entity.auto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "parts")
public class Part {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column(name = "part_Number")
    private String partNumber;

    @Column(name = "part_Name")
    private String partName;

    @Column(name = "count")
    private int count;

    @Column(name = "price")
    private double price;

//    @Column(name = "part_count")
//    private int partCount;

//    @OneToOne(mappedBy = "part", cascade = {CascadeType.ALL})
//    private PartCount partCount;

    @ManyToMany(mappedBy = "parts")
    private Set<ServiceOrder> serviceOrderSet = new HashSet<>();


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Part part = (Part) o;
        return Double.compare(part.price, price) == 0 && partNumber.equals(part.partNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(partNumber, price);
    }
}
