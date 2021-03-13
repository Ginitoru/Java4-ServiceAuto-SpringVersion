package com.gini.iordache.entity.labor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class LaborPrice {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false)
    private double mechanicalLaborPrice;

    @Column(nullable = false)
    private double electricalLaborPrice;

    @Column(nullable = false)
    private double normalLaborPrice;

    @Column(nullable = false)
    private double itpDieselEnginePrice;

    @Column(nullable = false)
    private double itpGasolineEnginePrice;

    @Column(nullable = false)
    private double itpSuvPrice;

    @Column(nullable = false)
    private double itpTruckPrice;

}
