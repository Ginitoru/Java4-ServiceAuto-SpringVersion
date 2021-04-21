package com.gini.iordache.convertor;

import com.gini.iordache.entity.auto.Part;
import com.gini.iordache.entity.order.PartOrder;
import com.gini.iordache.entity.order.ServiceOrder;

public class PartConvertor {

    private PartConvertor() {
    }


    public static PartOrder convert(Part part, ServiceOrder serviceOrder, int count){

        return new PartOrder(
                             part.getPartNumber(),
                             part.getPartName(),
                             count,
                             part.getPrice(),
                             serviceOrder
        );
    }
}
