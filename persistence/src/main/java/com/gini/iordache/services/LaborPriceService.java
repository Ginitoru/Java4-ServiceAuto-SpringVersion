package com.gini.iordache.services;

import com.gini.iordache.entity.labor.LaborPrice;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

public interface LaborPriceService {



    @Transactional
    void findLaborPrices();

    @Transactional
    LaborPrice findAllPrices();

    @Transactional
    void createAllLaborPrices(LaborPrice laborPrice);

    @Transactional
    int updateMechanicalLaborPrice(double mechanicalLaborPrice);

    @Transactional
    int updateElectricalLaborPrice(double electricalLaborPrice);

    @Transactional
    int updateNormalLaborPrice(double normalLaborPrice);

    @Transactional
    int updateItpDieselEnginePrice(double itpDieselEnginePrice);

    @Transactional
    int updateItpGasolineEnginePrice(double itpGasolineEnginePrice);

    @Transactional
    int updateItpSuvPrice(double itpSuvPrice);

    @Transactional
    int updateItpTruckPrice(double itpTruckPrice);
}
