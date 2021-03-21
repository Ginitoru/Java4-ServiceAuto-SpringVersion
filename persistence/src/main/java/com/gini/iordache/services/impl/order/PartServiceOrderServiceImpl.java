package com.gini.iordache.services.impl.order;


import com.gini.iordache.convertor.PartConvertor;
import com.gini.iordache.dao.iterfaces.PartDao;
import com.gini.iordache.dao.iterfaces.PartServiceOrderDao;
import com.gini.iordache.entity.auto.Part;
import com.gini.iordache.entity.order.PartServiceOrder;
import com.gini.iordache.entity.order.ServiceOrder;
import com.gini.iordache.services.interfaces.PartServiceOrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class PartServiceOrderServiceImpl implements PartServiceOrderService {

    private final PartServiceOrderDao partServiceOrderDao;
    private final PartDao partDao;

    @Override
    @Transactional
    public void addPartToServiceOrder(Part part, ServiceOrder serviceOrder, int count, double price){

        PartServiceOrder partServiceOrder = PartConvertor.convert(part,serviceOrder, count, price);

        partServiceOrderDao.createPartServiceOrder(partServiceOrder);
        partDao.decreasePartCount(count, part.getPartNumber());

    }



}
