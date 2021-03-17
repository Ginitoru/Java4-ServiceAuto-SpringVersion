package com.gini.iordache.services.impl.labor;

import com.gini.iordache.dao.LaborPriceDao;
import com.gini.iordache.entity.labor.LaborPrice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.PostConstruct;
import java.util.Optional;


@Service
public class LaborPriceServiceImpl implements com.gini.iordache.services.LaborPriceService {


    private final LaborPriceDao laborPriceDao;
    private Optional<LaborPrice> optLaborPrice;


    @Autowired
    public LaborPriceServiceImpl(LaborPriceDao laborPriceDao) {
        this.laborPriceDao = laborPriceDao;
    }



    //am nevoie de metoda aceasta deoarece metoda findLaborPrices() da eroare cand folosesc @PostConstruct
    // zice ca nu vede tranzactia deoarece contextul nu este inca incarcat => dupa ce se incarca
    //contextul se apeleaza si findLaborPrices()

    //https://www.devpragmatic.com/2017/01/spring-transakcje-init-method.html
    //https://stackoverflow.com/questions/17346679/transactional-on-postconstruct-method
    @EventListener(ContextRefreshedEvent.class)
    public void appContextLoaded(ContextRefreshedEvent evt){
        evt.getApplicationContext()
                .getBean(LaborPriceServiceImpl.class)
                                        .findLaborPrices();
    }



    @Override
    @Transactional
    public void findLaborPrices(){
        optLaborPrice = laborPriceDao.findAllLaborPrices();
    }



    @Override
    @Transactional
    public LaborPrice findAllPrices(){

        return optLaborPrice.orElseGet(LaborPrice::new);

    }



    @Override
    @Transactional
    public void createAllLaborPrices(LaborPrice laborPrice){

        findLaborPrices();

        if(optLaborPrice.isEmpty()){
            laborPriceDao.createAllLaborPrices(laborPrice);
            findLaborPrices();
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
