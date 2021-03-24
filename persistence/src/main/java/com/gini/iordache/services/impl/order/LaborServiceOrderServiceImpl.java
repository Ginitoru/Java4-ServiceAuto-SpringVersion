package com.gini.iordache.services.impl.order;


import com.gini.iordache.convertor.LaborConvertor;
import com.gini.iordache.dao.iterfaces.LaborServiceOrderDao;
import com.gini.iordache.entity.labor.Labor;
import com.gini.iordache.entity.labor.LaborPrice;
import com.gini.iordache.entity.order.LaborServiceOrder;
import com.gini.iordache.entity.order.ServiceOrder;
import com.gini.iordache.services.impl.labor.LaborPriceServiceImpl;
import com.gini.iordache.services.interfaces.LaborServiceOrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@AllArgsConstructor
@Service
public class LaborServiceOrderServiceImpl implements LaborServiceOrderService {

    private final LaborServiceOrderDao laborServiceOrderDao;
    private final LaborPriceServiceImpl laborPriceService;



    //method 1
    @Override
    @Transactional
    public void addLaborToServiceOrder(Labor labor, ServiceOrder serviceOrder){

         double laborPrice = this.laborPrice(labor);

         LaborServiceOrder laborServiceOrder = LaborConvertor.convert(labor,laborPrice, serviceOrder);


         laborServiceOrderDao.createLaborServiceOrder(laborServiceOrder);



    }


    //method 2
    private double laborPrice(Labor labor){


        String laborCategory = labor.getCategory().toString();
        Optional<LaborPrice>  laborPrice = laborPriceService.getOptLaborPrice();

        if(laborPrice.isPresent()){

                switch (laborCategory){


                    case "MECHANICAL" -> {
                        return labor.getTimedLabor() * laborPrice.get().getMechanicalLaborPrice();

                    }


                    case "BODY" -> {
                        return labor.getTimedLabor() * laborPrice.get().getBodyLaborPrice();
                    }


                    case "ELECTRIC" -> {
                        return labor.getTimedLabor() * laborPrice.get().getElectricalLaborPrice();
                    }


                    case "NORMAL" -> {
                        return labor.getTimedLabor() * laborPrice.get().getNormalLaborPrice();
                    }


                    case "ITP_DIESEL" -> {
                        return labor.getTimedLabor() * laborPrice.get().getItpDieselEnginePrice();
                    }


                    case "ITP_GASOLINE" -> {
                        return labor.getTimedLabor() * laborPrice.get().getItpGasolineEnginePrice();
                    }


                    case "ITP_SUV" -> {
                        return labor.getTimedLabor() * laborPrice.get().getItpSuvPrice();
                    }


                    case "ITP_TRUCK" -> {
                        return labor.getTimedLabor() * laborPrice.get().getItpTruckPrice();
                    }


                }

        }

       return 0.0;
    }


    @Override
    @Transactional
    public void deleteLaborFromOrder(int id){

        Optional<LaborServiceOrder> laborOrder = laborServiceOrderDao.findLaborOrderById(id);

        if(laborOrder.isPresent()){
            laborServiceOrderDao.deleteLaborFromOrder(id);
            return;
        }


        throw new RuntimeException("Labor not found in order!");


    }

}
