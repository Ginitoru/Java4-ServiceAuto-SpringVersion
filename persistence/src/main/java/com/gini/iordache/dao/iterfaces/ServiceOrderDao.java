package com.gini.iordache.dao.iterfaces;

import com.gini.iordache.dto.PartDto;
import com.gini.iordache.dto.ServiceOrderIdAndStatusDto;
import com.gini.iordache.entity.order.PartServiceOrder;
import com.gini.iordache.entity.order.ServiceOrder;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ServiceOrderDao {
    void createServiceOrder(ServiceOrder serviceOrder);


    Set<ServiceOrder> findAllServiceOrders();

    List<ServiceOrderIdAndStatusDto> allServiceOrderIdAndStatus();

    Optional<ServiceOrder> findServiceOrderById(int id);

    ServiceOrder updateServiceOrder(ServiceOrder serviceOrder);


    ServiceOrder findServiceOrderParts(int id);

    List<PartServiceOrder> getPartsFormServiceOrder(int id);
}
