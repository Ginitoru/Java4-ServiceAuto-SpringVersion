package com.gini.iordache.convertor;

import com.gini.iordache.entity.auto.Part;
import com.gini.iordache.entity.order.PartServiceOrder;

public class PartConvertor {

    private PartConvertor() {
    }


    public static PartServiceOrder convert(Part part, int count, double price){

        PartServiceOrder partServiceOrder = new PartServiceOrder(
                                                part.getPartNumber(),
                                                part.getPartName(),
                                                count,
                                                price);


        return partServiceOrder;
    }


}
