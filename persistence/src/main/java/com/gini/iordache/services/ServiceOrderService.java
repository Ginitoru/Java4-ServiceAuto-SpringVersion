package com.gini.iordache.services;

import com.gini.iordache.dto.ServiceOrderIdAndStatusDto;
import com.gini.iordache.entity.auto.ServiceOrder;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

public interface ServiceOrderService {
    @Transactional
    void createServiceOrder(ServiceOrder serviceOrder);

    @Transactional
    Set<ServiceOrder> findAllServiceOrder();

    @Transactional
    List<ServiceOrderIdAndStatusDto> allServiceOrderIdAndStatus();
}
