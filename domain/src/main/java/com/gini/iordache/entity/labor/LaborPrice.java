package com.gini.iordache.entity.labor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
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
    @Min(value = 0, message = "invalid")
    private Double mechanicalLaborPrice;

    @NotNull(message = "required")
    @Min(value = 0, message = "invalid")
    private Double bodyLaborPrice;

    @NotNull(message = "required")
    @Min(value = 0, message = "invalid")
    private Double electricalLaborPrice;

    @NotNull(message = "required")
    @Min(value = 0, message = "invalid")
    private Double normalLaborPrice;

    @NotNull(message = "required")
    @Min(value = 0, message = "invalid")
    private Double itpDieselEnginePrice;

    @NotNull(message = "required")
    @Min(value = 0, message = "invalid")
    private Double itpGasolineEnginePrice;

    @NotNull(message = "required")
    @Min(value = 0, message = "invalid")
    private Double itpSuvPrice;

    @NotNull(message = "required")
    @Min(value = 0, message = "invalid")
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
