package com.gini.iordache.services.interfaces;

import com.gini.iordache.entity.order.PartServiceOrder;
import org.springframework.transaction.annotation.Transactional;

public interface PartServiceOrderService {
    @Transactional
    void createPartServiceOrder(PartServiceOrder partServiceOrder);
}
