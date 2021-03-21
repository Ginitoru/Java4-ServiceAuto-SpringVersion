package com.gini.iordache.services.interfaces;


import com.gini.iordache.entity.order.LaborServiceOrder;
import org.springframework.transaction.annotation.Transactional;

public interface LaborServiceOrderService {

    @Transactional
    void createLaborServiceOrder(LaborServiceOrder laborServiceOrder);
}
