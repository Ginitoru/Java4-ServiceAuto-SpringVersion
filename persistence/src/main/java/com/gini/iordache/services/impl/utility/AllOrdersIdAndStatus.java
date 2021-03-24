package com.gini.iordache.services.impl.utility;


import com.gini.iordache.dto.ServiceOrderIdAndStatusDto;
import com.gini.iordache.services.impl.order.ServiceOrderServiceImpl;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;


import java.util.concurrent.CopyOnWriteArrayList;


//@Getter
//@Setter
//@Component
public class AllOrdersIdAndStatus {


//
//    private CopyOnWriteArrayList<ServiceOrderIdAndStatusDto> list = new CopyOnWriteArrayList<>();
//
//
//    @EventListener(ContextRefreshedEvent.class)
//    public void allServiceOrderIdAndStatus(ContextRefreshedEvent evt){
//
//            list                                                    // -> dupa ce incarca contextul incar id-urile si staturile service orederuliro
//                .addAll( evt.getApplicationContext()
//                            .getBean(ServiceOrderServiceImpl.class)
//                                            .allServiceOrderIdAndStatus());
//
//
//    }





}
