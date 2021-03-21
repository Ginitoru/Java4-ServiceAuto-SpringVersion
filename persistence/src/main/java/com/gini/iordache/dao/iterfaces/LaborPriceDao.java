package com.gini.iordache.dao.iterfaces;

import com.gini.iordache.entity.labor.LaborPrice;

import java.util.Optional;

public interface LaborPriceDao {
    void createAllLaborPrices(LaborPrice laborPrice);

    Optional<LaborPrice> findAllLaborPrices();

    int updateMechanicalLaborPrice(double mechanicalLaborPrice, int id);

    int updateBodyLaborPrice(double bodyLaborPrice, int id);

    int updateElectricalLaborPrice(double electricalLaborPrice, int id);

    int updateNormalLaborPrice(double normalLaborPrice, int id);

    int updateItpDieselEnginePrice(double itpDieselEnginePrice, int id);

    int updateItpGasolineEnginePrice(double itpGasolineEnginePrice, int id);

    int updateItpSuvPrice(double itpSuvPrice, int id);

    int updateItpTruckPrice(double itpTruckPrice, int id);
}
