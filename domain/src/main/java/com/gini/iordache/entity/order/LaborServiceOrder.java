package com.gini.iordache.entity.order;


import com.gini.iordache.utility.LaborCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class LaborServiceOrder {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String laborDescription;

    private double timedLabor;

    private double laborPrice;

    @ManyToOne
    private ServiceOrder serviceOrder;

    @Enumerated(EnumType.STRING)
    private LaborCategory category;

    public LaborServiceOrder(String laborDescription, double timedLabor, double laborPrice, ServiceOrder serviceOrder, LaborCategory category) {
        this.laborDescription = laborDescription;
        this.timedLabor = timedLabor;
        this.laborPrice = laborPrice;
        this.serviceOrder = serviceOrder;
        this.category = category;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LaborServiceOrder that = (LaborServiceOrder) o;
        return Double.compare(that.timedLabor, timedLabor) == 0 && Double.compare(that.laborPrice, laborPrice) == 0 && Objects.equals(laborDescription, that.laborDescription) && category == that.category;
    }

    @Override
    public int hashCode() {
        return Objects.hash(laborDescription, timedLabor, laborPrice, category);
    }

    @Override
    public String toString() {
        return "LaborServiceOrder{" +
                "id=" + id +
                ", laborDescription='" + laborDescription + '\'' +
                ", timedLabor=" + timedLabor +
                ", laborPrice=" + laborPrice +
                ", category=" + category +
                '}';
    }
}
