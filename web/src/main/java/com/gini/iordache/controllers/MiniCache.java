package com.gini.iordache.controllers;

import com.gini.iordache.entity.auto.Part;
import com.gini.iordache.entity.labor.Labor;
import com.gini.iordache.entity.order.LaborOrder;
import com.gini.iordache.entity.order.ServiceOrder;
import com.gini.iordache.services.interfaces.LaborService;
import com.gini.iordache.services.interfaces.PartService;
import com.gini.iordache.services.interfaces.ServiceOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class MiniCache {  //todo: de vazut cum inlocuiesc asta cu un cache


    private final ServiceOrderService serviceOrderService;
    private final LaborService laborService;
    private final PartService partService;
    private static final Map<String, ServiceOrder> order = new HashMap<>();
    private final HashMap<String, List<Labor>> labors = new HashMap<>();
    private final HashMap<String, List<LaborOrder>> orderLabors = new HashMap<>();
    private final Map<String, Part> parts = new HashMap<>();

    @Autowired
    public MiniCache(ServiceOrderService serviceOrderService, LaborService laborService, PartService partService) {
        this.serviceOrderService = serviceOrderService;
        this.laborService = laborService;
        this.partService = partService;
    }

    public ServiceOrder loadCompleteServiceOrderById(int id){

        var serviceOrder = serviceOrderService.findCompleteServiceOrderById(id);

        order.put(username(), serviceOrder);
        return order.get(username());
    }


    public ServiceOrder getCompleteServiceOrder(){
        return order.get(username());
    }


    public void loadLaborsOrder(){
        orderLabors.put(username(), serviceOrderService.
                                                 findAllLaborsInOrder(getCompleteServiceOrder().getId()));
    }


    public List<LaborOrder> getLaborFromOrder(){
        return orderLabors.get(username());
    }


    public void loadLabors(String laborDescription){
        labors.put(username(), laborService.findLaborByName(laborDescription));
    }


    public List<Labor> getLabors(){
       return labors.get(username());
    }



    public String username(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }


    public Part findPartByPartNumber(String partNumber){
        parts.put(username(),partService.findPartByPartNumber(partNumber));

        return parts.get(username());
    }

    public Part getPart(){
        return parts.get(username());
    }






}
