package com.gini.iordache.convertor;

import com.gini.iordache.entity.labor.Labor;
import com.gini.iordache.entity.order.LaborOrder;
import com.gini.iordache.entity.order.ServiceOrder;

public class LaborConvertor {

    private LaborConvertor() {
    }


    public static LaborOrder convert(Labor labor, double laborPrice, ServiceOrder serviceOrder){

        LaborOrder laborServiceOrder = new LaborOrder(
                                                                labor.getLaborDescription(),
                                                                labor.getTimedLabor(),
                                                                laborPrice,
                                                                serviceOrder,
                                                                labor.getCategory()

        );


        return laborServiceOrder;

    }
}
