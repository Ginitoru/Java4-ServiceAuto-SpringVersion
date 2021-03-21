package com.gini.iordache.convertor;

import com.gini.iordache.entity.auto.Part;
import com.gini.iordache.entity.order.PartServiceOrder;
import com.gini.iordache.entity.order.ServiceOrder;

public class PartConvertor {

    private PartConvertor() {
    }


    public static PartServiceOrder convert(Part part, ServiceOrder serviceOrder, int count){

        PartServiceOrder partServiceOrder = new PartServiceOrder(
                                                part.getPartNumber(),
                                                part.getPartName(),
                                                count,
                                                part.getPrice(),
                                                serviceOrder);


        return partServiceOrder;
    }


}
