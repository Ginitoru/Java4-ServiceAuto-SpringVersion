package com.gini.iordache.services.impl.auto;



import com.gini.iordache.dao.PartDao;

import com.gini.iordache.entity.auto.Part;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@AllArgsConstructor
@Service
public class PartServiceImpl implements com.gini.iordache.services.PartService {

    private final PartDao partDao;


    @Override
    @Transactional
    public void addPart(Part part){

        Optional<Part> optionalPart = partDao.findPartByPartNumber(part.getPartNumber());


        if(optionalPart.isEmpty()){                  //daca piesa nu exista o creeaza
            partDao.createPart(part);
            return;
        }



        if(optionalPart.get().equals(part)){          //daca piesa exista si are acelasi pret face update la count in warehouse
            partDao.updatePartCount(part.getCount(), part.getPartNumber());
            return;
        }


        if(!optionalPart.get().equals(part)){        //daca piesa exista si are alt pret o creeaza iar
            partDao.createPart(part);
        }
    }
}
