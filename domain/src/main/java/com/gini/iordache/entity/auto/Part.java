package com.gini.iordache.entity.auto;

import com.gini.iordache.entity.order.ServiceOrder;
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

    @Column(name = "count")
    private int count;

    @Column(name = "price")
    private double price;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Part part = (Part) o;
        return Objects.equals(partNumber, part.partNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(partNumber);
    }

    @Override
    public String toString() {
        return "Part{" +
                "id=" + id +
                ", partNumber='" + partNumber + '\'' +
                ", partName='" + partName + '\'' +
                ", count=" + count +
                ", price=" + price +
                '}';
    }
}
