package com.gini.iordache.services.impl.labor;

import com.gini.iordache.dao.LaborPriceDao;
import com.gini.iordache.entity.labor.LaborPrice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.PostConstruct;
import java.util.Optional;


@Service
public class LaborPriceServiceImpl implements com.gini.iordache.services.LaborPriceService {


    private final LaborPriceDao laborPriceDao;
    private Optional<LaborPrice> optLaborPrice;


    public LaborPriceServiceImpl(LaborPriceDao laborPriceDao) {
        this.laborPriceDao = laborPriceDao;
    }


    @PostConstruct
    private void findLaborPrices(){
        optLaborPrice = laborPriceDao.findAllLaborPrices();
    }


    @Override
    @Transactional
    public void createAllLaborPrices(LaborPrice laborPrice){

        if(optLaborPrice.isEmpty()){
            laborPriceDao.createAllLaborPrices(laborPrice);
            return;
        }

        throw new RuntimeException("Prices are already created!");

    }



    //TODO:
    //TODO:
    //TODO: DE VAZUT CUM FAC SA BAGA TOATE METODELE ASTEA INTR-UN SWITCH CA PREA SEAMANA INTRE ELE.




    @Override
    @Transactional
    public int updateMechanicalLaborPrice(double mechanicalLaborPrice){

        if(optLaborPrice.isPresent()){

            var id = optLaborPrice.get().getId();

           return laborPriceDao.updateMechanicalLaborPrice(mechanicalLaborPrice, id);
        }

        throw new RuntimeException("To update the the prices you have to create them first!");

    }


    @Override
    @Transactional
    public int updateElectricalLaborPrice(double electricalLaborPrice){

        if(optLaborPrice.isPresent()){

            var id = optLaborPrice.get().getId();

            return laborPriceDao.updateElectricalLaborPrice(electricalLaborPrice, id);
        }

        throw new RuntimeException("To update the the prices you have to create them first!");
    }


    @Override
    @Transactional
    public int updateNormalLaborPrice(double normalLaborPrice){

        if(optLaborPrice.isPresent()){

            var id = optLaborPrice.get().getId();

            return laborPriceDao.updateNormalLaborPrice(normalLaborPrice, id);
        }

        throw new RuntimeException("To update the the prices you have to create them first!");
    }


    @Override
    @Transactional
    public int updateItpDieselEnginePrice(double itpDieselEnginePrice){

        if(optLaborPrice.isPresent()){

            var id = optLaborPrice.get().getId();

            return laborPriceDao.updateItpDieselEnginePrice(itpDieselEnginePrice, id);
        }

        throw new RuntimeException("To update the the prices you have to create them first!");
    }


    @Override
    @Transactional
    public int updateItpGasolineEnginePrice(double itpGasolineEnginePrice){

        if(optLaborPrice.isPresent()){

            var id = optLaborPrice.get().getId();

            return laborPriceDao.updateItpGasolineEnginePrice(itpGasolineEnginePrice, id);
        }

        throw new RuntimeException("To update the the prices you have to create them first!");
    }


    @Override
    @Transactional
    public int updateItpSuvPrice(double itpSuvPrice){

        if(optLaborPrice.isPresent()){

            var id = optLaborPrice.get().getId();

            return laborPriceDao.updateItpSuvPrice(itpSuvPrice, id);
        }

        throw new RuntimeException("To update the the prices you have to create them first!");
    }


    @Override
    @Transactional
    public int updateItpTruckPrice(double itpTruckPrice){

        if(optLaborPrice.isPresent()){

            var id = optLaborPrice.get().getId();

            return laborPriceDao.updateItpTruckPrice(itpTruckPrice, id);
        }

        throw new RuntimeException("To update the the prices you have to create them first!");

    }



}
