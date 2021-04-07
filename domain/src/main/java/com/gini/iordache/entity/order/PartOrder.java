package com.gini.iordache.entity.order;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;


@NoArgsConstructor
@Getter
@Setter
@Entity
public class PartOrder {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "part_Number")
    private String partNumber;

    @Column(name = "part_Name")
    private String partName;

    @Column(name = "count")
    private int count;

    @Column(name = "price")
    private double price;

    @ManyToOne
    private ServiceOrder serviceOrder;


    public PartOrder(String partNumber, String partName, int count, double price, ServiceOrder serviceOrder) {
        this.partNumber = partNumber;
        this.partName = partName;
        this.count = count;
        this.price = price;
        this.serviceOrder = serviceOrder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PartOrder that = (PartOrder) o;
        return Objects.equals(partNumber, that.partNumber) && Objects.equals(partName, that.partName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(partNumber, partName);
    }

    @Override
    public String toString() {
        return "PartServiceOrder{" +
                "id=" + id +
                ", partNumber='" + partNumber + '\'' +
                ", partName='" + partName + '\'' +
                ", count=" + count +
                ", price=" + price +
                '}';
    }
}
