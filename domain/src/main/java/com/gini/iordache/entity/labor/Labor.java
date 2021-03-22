package com.gini.iordache.entity.labor;

import com.gini.iordache.utility.LaborCategory;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;


@NoArgsConstructor
@Getter
@Setter
@Entity
public class Labor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String laborDescription;

    private double timedLabor;

    @Enumerated(EnumType.STRING)
    private LaborCategory category;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Labor labor = (Labor) o;
        return Double.compare(labor.timedLabor, timedLabor) == 0 && Objects.equals(laborDescription, labor.laborDescription) && category == labor.category;
    }

    @Override
    public int hashCode() {
        return Objects.hash(laborDescription, timedLabor, category);
    }


    @Override
    public String toString() {
        return "Labor{" +
                "id=" + id +
                ", laborDescription='" + laborDescription + '\'' +
                ", timedLabor=" + timedLabor +
                ", category=" + category +
                '}';
    }
}
