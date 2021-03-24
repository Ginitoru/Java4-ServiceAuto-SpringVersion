package com.gini.iordache.services.interfaces;



import com.gini.iordache.dto.ServiceOrderIdAndStatusDto;
import com.gini.iordache.entity.order.LaborServiceOrder;
import com.gini.iordache.entity.order.PartServiceOrder;
import com.gini.iordache.entity.order.ServiceOrder;
import com.gini.iordache.utility.OrderStatus;

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

    @Transactional
    List<LaborServiceOrder> findAllLaborsInOrder(int id);

    @Transactional
    ServiceOrder findCompleteServiceOrderById(int id);


    @Transactional
    int updateOrderStatus(OrderStatus orderStatus, int id);
}
