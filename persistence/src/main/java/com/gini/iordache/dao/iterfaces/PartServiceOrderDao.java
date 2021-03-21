package com.gini.iordache.dao.iterfaces;

import com.gini.iordache.entity.order.PartServiceOrder;
import com.gini.iordache.entity.order.ServiceOrder;

import java.util.Optional;

public interface PartServiceOrderDao {
    void createPartServiceOrder(PartServiceOrder partServiceOrder);

    Optional<PartServiceOrder> findPartOrderByPartName(String partNumber, ServiceOrder serviceOrder);

    int deletePartFromServiceOrder(String partNumber);

    int updatePartOrderCount(int id, int increment);
}
