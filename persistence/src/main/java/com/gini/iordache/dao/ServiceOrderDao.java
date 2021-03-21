package com.gini.iordache.dao;

import com.gini.iordache.dto.ServiceOrderIdAndStatusDto;
import com.gini.iordache.entity.auto.ServiceOrder;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ServiceOrderDao {
    void createServiceOrder(com.gini.iordache.entity.auto.ServiceOrder serviceOrder);


    Set<ServiceOrder> findAllServiceOrders();

    List<ServiceOrderIdAndStatusDto> allServiceOrderIdAndStatus();

    Optional<ServiceOrder> findServiceOrderById(int id);

    ServiceOrder updateServiceOrder(ServiceOrder serviceOrder);


    ServiceOrder findServiceOrderParts(int id);
}
