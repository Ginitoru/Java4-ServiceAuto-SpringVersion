package com.gini.iordache.entity.labor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class LaborPrice {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotNull(message = "required")
    @Digits(fraction = 2, integer = 3, message = "naspa")
    private Double mechanicalLaborPrice;

    @NotNull(message = "required")
    private Double bodyLaborPrice;

    @NotNull(message = "required")
    private Double electricalLaborPrice;

    @NotNull(message = "required")
    private Double normalLaborPrice;

    @NotNull(message = "required")
    private Double itpDieselEnginePrice;

    @NotNull(message = "required")
    private Double itpGasolineEnginePrice;

    @NotNull(message = "required")
    private Double itpSuvPrice;

    @NotNull(message = "required")
    private Double itpTruckPrice;


    @Override
    public String toString() {
        return "LaborPrice{" +
                "id=" + id +
                ", mechanicalLaborPrice=" + mechanicalLaborPrice +
                ", electricalLaborPrice=" + electricalLaborPrice +
                ", normalLaborPrice=" + normalLaborPrice +
                ", itpDieselEnginePrice=" + itpDieselEnginePrice +
                ", itpGasolineEnginePrice=" + itpGasolineEnginePrice +
                ", itpSuvPrice=" + itpSuvPrice +
                ", itpTruckPrice=" + itpTruckPrice +
                '}';
    }
}
