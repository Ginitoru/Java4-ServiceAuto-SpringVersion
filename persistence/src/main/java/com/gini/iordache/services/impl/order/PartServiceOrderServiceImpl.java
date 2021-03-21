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

import java.util.Optional;

@AllArgsConstructor
@Service
public class PartServiceOrderServiceImpl implements PartServiceOrderService {

    private final PartServiceOrderDao partServiceOrderDao;
    private final PartDao partDao;

    @Override
    @Transactional
    public void addPartToServiceOrder(Part part, ServiceOrder serviceOrder, int count){

        Optional<PartServiceOrder> optPartOrder = partServiceOrderDao.findPartOrderByPartName(part.getPartNumber(), serviceOrder);
        PartServiceOrder partServiceOrder = PartConvertor.convert(part,serviceOrder, count);


        if(count <= part.getCount()){

            if(optPartOrder.isEmpty()){

                partServiceOrderDao.createPartServiceOrder(partServiceOrder);                //adauga piesa in comanda daca nu exista

            }else{

                partServiceOrderDao.updatePartOrderCount(optPartOrder.get().getId(), count); // daca piesa exista in comanda si o adaugam iar ii va creste
            }                                                                                    // nr de bucati din comanda


            partDao.decreasePartCount(count, part.getPartNumber());                              //scade nr de piese pe care le baga in comanda din magazie
            return;

        }

        throw new RuntimeException("Not enough parts in WAREHOUSE!!! ");

    }


    @Override
    @Transactional
    public int deletePartFromServiceOrder(String partNumber, int count){

        partDao.updatePartCount(count, partNumber);
        return partServiceOrderDao.deletePartFromServiceOrder(partNumber);

    }



}
