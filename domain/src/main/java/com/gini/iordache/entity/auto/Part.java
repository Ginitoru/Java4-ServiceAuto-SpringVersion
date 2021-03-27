package com.gini.iordache.entity.auto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Objects;



@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "parts")
public class Part {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotNull(message = "required")
    @Size(min = 1, message = "required")
    @Column(name = "part_Number")
    private String partNumber;

    @NotNull(message = "required")
    @Size(min = 1, message = "required")
    @Column(name = "part_Name")
    private String partName;


    @Min(value = 1, message = "must be equal or greater than {value}")
    @Max(value = 100000, message = "must be less or equal to {value}")
    @NotNull(message = "required")
    @Column(name = "count")
    private Integer count;

    @Min(value = 0, message = "must be equal or grater than {value}")
    @NotNull(message = "required")
    @Column(name = "price")
    private Double price;


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
