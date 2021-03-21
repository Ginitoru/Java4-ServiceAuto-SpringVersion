package com.gini.iordache.services.interfaces;

import com.gini.iordache.entity.auto.Part;
import com.gini.iordache.entity.order.PartServiceOrder;
import com.gini.iordache.entity.order.ServiceOrder;
import org.springframework.transaction.annotation.Transactional;

public interface PartServiceOrderService {

    @Transactional
    void addPartToServiceOrder(Part part, ServiceOrder serviceOrder, int count, double price);
}
