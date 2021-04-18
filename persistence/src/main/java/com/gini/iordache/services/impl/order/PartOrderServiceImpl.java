package com.gini.iordache.services.impl.order;


import com.gini.errors.order.PartOrderException;
import com.gini.errors.order.NotEnoughPartsException;
import com.gini.iordache.convertor.PartConvertor;
import com.gini.iordache.dao.iterfaces.PartDao;
import com.gini.iordache.dao.iterfaces.PartOrderDao;
import com.gini.iordache.dao.iterfaces.OrderDao;
import com.gini.iordache.entity.auto.Part;
import com.gini.iordache.entity.order.PartOrder;
import com.gini.iordache.entity.order.ServiceOrder;
import com.gini.iordache.services.interfaces.PartOrderService;
import com.gini.iordache.utility.OrderStatus;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@AllArgsConstructor
@Service
public class PartOrderServiceImpl implements PartOrderService {

    private final PartOrderDao partOrderDao;
    private final PartDao partDao;
    private final OrderDao orderDao;

    @Override
    @Transactional
    public void addPartToServiceOrder(Part part, ServiceOrder serviceOrder, int count){


        if((serviceOrder.getOrderStatus().toString().equals("CLOSE"))){
            throw new PartOrderException("Order is CLOSED, can't add any more parts to it!");
        }


        Optional<PartOrder> optPartOrder = partOrderDao.findPartOrderByPartName(part.getPartNumber(), serviceOrder);
        PartOrder partServiceOrder = PartConvertor.convert(part,serviceOrder, count);  //-> transform piesa din warehouse intro piesa ce va intra in comanda


        if((count <= part.getCount())){


            if(optPartOrder.isEmpty()){

                partOrderDao.createPartOrder(partServiceOrder);                //adauga piesa in comanda daca nu exista

            }else{

                partOrderDao.updatePartOrderCount(optPartOrder.get().getId(), count); // daca piesa exista in comanda si o adaugam iar ii va creste
            }                                                                                    // nr de bucati din comanda



            int id = serviceOrder.getId();

            orderDao.updateOrderStatus(OrderStatus.READY, id);
            partDao.decreasePartCount(count, part.getPartNumber());                              //scade nr de piese pe care le baga in comanda din magazie
            return;

        }

        throw new NotEnoughPartsException("Not enough parts in WAREHOUSE!!! ");

    }


    @Override
    @Transactional
    public int deletePartFromServiceOrder(String partNumber, int count, ServiceOrder order){

        if(order.getOrderStatus().toString().equals("CLOSE")){
            throw new PartOrderException("Order is CLOSED can't remove parts from it!");
        }

        partDao.updatePartCount(count, partNumber);
        return partOrderDao.deletePartFromServiceOrder(partNumber);

    }



}
