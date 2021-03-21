package com.gini.iordache.services.impl.order;


import com.gini.iordache.dao.iterfaces.PartServiceOrderDao;
import com.gini.iordache.entity.order.PartServiceOrder;
import com.gini.iordache.services.interfaces.PartService;
import com.gini.iordache.services.interfaces.PartServiceOrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class PartServiceOrderServiceImpl implements PartServiceOrderService {

    private final PartServiceOrderDao partServiceOrderDao;
    private final PartService partService;

    @Override
    @Transactional
    public void createPartServiceOrder(PartServiceOrder partServiceOrder){
        partServiceOrderDao.createPartServiceOrder(partServiceOrder);

    }

}
