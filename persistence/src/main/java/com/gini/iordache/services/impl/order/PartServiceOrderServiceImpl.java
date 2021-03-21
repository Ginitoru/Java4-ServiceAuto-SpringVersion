package com.gini.iordache.services.impl.order;


import com.gini.iordache.dao.PartServiceOrderDao;
import com.gini.iordache.entity.order.PartServiceOrder;
import com.gini.iordache.services.PartService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class PartServiceOrderServiceImpl implements com.gini.iordache.services.PartServiceOrderService {

    private final PartServiceOrderDao partServiceOrderDao;
    private final PartService partService;

    @Override
    @Transactional
    public void createPartServiceOrder(PartServiceOrder partServiceOrder){
        partServiceOrderDao.createPartServiceOrder(partServiceOrder);

    }

}
