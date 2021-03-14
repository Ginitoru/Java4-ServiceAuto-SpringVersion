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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "part_Number")
    private String partNumber;

    @Column(name = "part_Name")
    private String partName;

//    @Column(name = "count")
//    private int count;

    @Column(name = "price")
    private double price;

//    @Column(name = "part_count")
//    private int partCount;

    @OneToOne(mappedBy = "part", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private PartCount partCount;

    @ManyToMany(mappedBy = "parts")
    private Set<ServiceOrder> serviceOrderSet = new HashSet<>();


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Part part = (Part) o;
        return Objects.equals(partNumber, part.partNumber) && Objects.equals(partName, part.partName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(partNumber, partName);
    }
}
