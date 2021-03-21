package com.gini.iordache.services.impl.auto;



import com.gini.iordache.dao.iterfaces.PartDao;

import com.gini.iordache.entity.auto.Part;

import com.gini.iordache.services.interfaces.PartService;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@AllArgsConstructor
@Service
public class PartServiceImpl implements PartService {

    private final PartDao partDao;


    @Override
    @Transactional
    public void addPart(Part part){

        Optional<Part> optionalPart = partDao.findPartByPartNumber(part.getPartNumber());


        if(optionalPart.isEmpty()){                  //daca piesa nu exista o creeaza
            partDao.createPart(part);
            return;
        }



        if(optionalPart.get().equals(part)){        //daca piesa exista si are acelasi pret face update la count si pret in depozit

            partDao.updatePartCountAndPrice(
                                            part.getCount(),
                                                    part.getPrice(),
                                                         part.getPartNumber());
        }

    }


    @Override
    @Transactional
    public Part findPartByPartNumber(String partNumber){

        return partDao.findPartByPartNumber(partNumber)
                        .orElseThrow(() -> new RuntimeException("Part does not exists!"));


    }

    @Override
    @Transactional
    public int decreasePartCount(int decrement, String partNumber){

        return partDao
                    .decreasePartCount(decrement, partNumber);
    }

}
