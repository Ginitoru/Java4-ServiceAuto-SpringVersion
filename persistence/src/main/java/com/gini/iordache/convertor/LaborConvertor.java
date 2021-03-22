package com.gini.iordache.convertor;

import com.gini.iordache.entity.labor.Labor;
import com.gini.iordache.entity.order.LaborServiceOrder;
import com.gini.iordache.entity.order.ServiceOrder;

public class LaborConvertor {

    private LaborConvertor() {
    }


    public static LaborServiceOrder convert(Labor labor, double laborPrice, ServiceOrder serviceOrder){

        LaborServiceOrder laborServiceOrder = new LaborServiceOrder(
                                                                labor.getLaborDescription(),
                                                                labor.getTimedLabor(),
                                                                laborPrice,
                                                                serviceOrder,
                                                                labor.getCategory()

        );


        return laborServiceOrder;

    }
}
