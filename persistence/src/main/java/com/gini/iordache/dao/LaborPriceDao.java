package com.gini.iordache.dao;

import com.gini.iordache.entity.labor.LaborPrice;

public interface LaborPriceDao {
    void createLaborPrices(LaborPrice laborPrice);

    int updateMechanicalLaborPrice(double mechanicalLaborPrice, int id);

    int updateElectricalLaborPrice(double electricalLaborPrice, int id);

    int updateNormalLaborPrice(double normalLaborPrice, int id);

    int updateItpDieselEnginePrice(double itpDieselEnginePrice, int id);

    int updateItpGasolineEnginePrice(double itpGasolineEnginePrice, int id);

    int updateItpSuvPrice(double itpSuvPrice, int id);

    int updateItpTruckPrice(double itpTruckPrice, int id);
}
