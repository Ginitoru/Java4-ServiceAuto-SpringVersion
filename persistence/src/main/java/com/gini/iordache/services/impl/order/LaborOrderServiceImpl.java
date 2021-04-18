package com.gini.iordache.services.impl.order;


import com.gini.errors.order.LaborOrderException;
import com.gini.iordache.convertor.LaborConvertor;
import com.gini.iordache.dao.iterfaces.LaborOrderDao;
import com.gini.iordache.dao.iterfaces.OrderDao;
import com.gini.iordache.entity.labor.Labor;
import com.gini.iordache.entity.labor.LaborPrice;
import com.gini.iordache.entity.order.LaborOrder;
import com.gini.iordache.entity.order.ServiceOrder;
import com.gini.iordache.services.impl.labor.LaborPriceServiceImpl;
import com.gini.iordache.services.interfaces.LaborOrderService;
import com.gini.iordache.util.TwoDigitsDouble;
import com.gini.iordache.utility.OrderStatus;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@AllArgsConstructor
@Service
public class LaborOrderServiceImpl implements LaborOrderService {

    private final LaborOrderDao laborOrderDao;
    private final LaborPriceServiceImpl laborPriceService;
    private final OrderDao serviceOrderDao;


    //method 1
    @Override
    @Transactional
    public void addLaborToServiceOrder(Labor labor, ServiceOrder order){

        if(order.getOrderStatus().toString().equals("CLOSE")){
            throw new LaborOrderException("Order is CLOSED, can't add any more labors to it!");
        }


         double laborPrice = this.laborPrice(labor);
         laborPrice = TwoDigitsDouble.formatPrice(laborPrice); //formatez pretul la 2 cifre dupa punct

         LaborOrder laborServiceOrder = LaborConvertor.convert(labor,laborPrice, order);


         laborOrderDao.createLaborServiceOrder(laborServiceOrder);
         serviceOrderDao.updateOrderStatus(OrderStatus.READY, order.getId());

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
    public void deleteLaborFromOrder(int id, ServiceOrder order){

        if(order.getOrderStatus().toString().equals("CLOSE")){
            throw new LaborOrderException("Order is CLOSED, can't add any more labors to it!");
        }

        Optional<LaborOrder> laborOrder = laborOrderDao.findLaborOrderById(id);

        if(laborOrder.isPresent()){
            laborOrderDao.deleteLaborFromOrder(id);
            return;
        }


        throw new RuntimeException("Labor not found in order!");


    }

}
