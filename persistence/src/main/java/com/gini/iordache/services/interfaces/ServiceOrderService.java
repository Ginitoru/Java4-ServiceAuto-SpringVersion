package com.gini.iordache.services.interfaces;

import com.gini.iordache.dto.PartDto;
import com.gini.iordache.dto.ServiceOrderIdAndStatusDto;
import com.gini.iordache.entity.order.PartServiceOrder;
import com.gini.iordache.entity.order.ServiceOrder;

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

    @Transactional
    ServiceOrder findServiceOrderById(int id);


    @Transactional
    ServiceOrder updateServiceOrder(ServiceOrder serviceOrder, int decrement, String partNumber);

    @Transactional
    ServiceOrder findServiceOrderParts(int id);

    @Transactional
    List<PartServiceOrder> getPartsFormServiceOrder(int id);
}
