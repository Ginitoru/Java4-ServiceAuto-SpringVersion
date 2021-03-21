package com.gini.iordache.entity.order;


import com.gini.iordache.utility.LaborCategory;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;

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

    @Enumerated(EnumType.STRING)
    private LaborCategory category;

}
