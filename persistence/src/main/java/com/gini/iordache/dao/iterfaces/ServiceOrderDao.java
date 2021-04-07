package com.gini.iordache.dao.iterfaces;



import com.gini.iordache.dto.ServiceOrderIdAndStatusDto;
import com.gini.iordache.entity.order.LaborOrder;
import com.gini.iordache.entity.order.PartOrder;
import com.gini.iordache.entity.order.ServiceOrder;
import com.gini.iordache.utility.OrderStatus;

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

    List<PartOrder> getPartsFormServiceOrder(int id);

    List<LaborOrder> findAllLaborsInOrder(int id);


    ServiceOrder findCompleteServiceOrderById(int id);


    int updateOrderStatus(OrderStatus orderStatus, int id);
}
