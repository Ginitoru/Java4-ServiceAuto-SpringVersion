package com.gini.iordache.services.interfaces;


import com.gini.iordache.entity.labor.Labor;
import com.gini.iordache.entity.order.ServiceOrder;
import org.springframework.transaction.annotation.Transactional;

public interface LaborOrderService {



    @Transactional
    void addLaborToServiceOrder(Labor labor, ServiceOrder serviceOrder);

    @Transactional
    void deleteLaborFromOrder(int id, ServiceOrder order);
}
