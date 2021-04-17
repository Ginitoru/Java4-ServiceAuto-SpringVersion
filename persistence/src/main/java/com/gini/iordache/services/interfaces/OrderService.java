package com.gini.iordache.services.interfaces;



import com.gini.iordache.dto.ServiceOrderIdAndStatusDto;
import com.gini.iordache.entity.order.LaborOrder;
import com.gini.iordache.entity.order.PartOrder;
import com.gini.iordache.entity.order.ServiceOrder;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

public interface OrderService {
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
    List<PartOrder> getPartsFormServiceOrder(int id);

    @Transactional
    List<LaborOrder> findAllLaborsInOrder(int id);

    @Transactional
    ServiceOrder findCompleteServiceOrderById(int id);


    @Transactional
    int closeOrder(ServiceOrder serviceOrder);
}
