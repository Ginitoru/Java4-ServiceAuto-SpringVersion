package com.gini.iordache.services.impl.labor;

import com.gini.iordache.dao.LaborDao;
import com.gini.iordache.entity.labor.Labor;
import com.gini.iordache.services.LaborService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class LaborServiceImpl implements LaborService {

    private final LaborDao laborDao;


    @Override
    @Transactional
    public void createLabor(Labor labor){

        laborDao.createLabor(labor);
    }


    @Override
    @Transactional
    public List<Labor> findAllLabors(){

       return laborDao.findAllLabors();
    }


    @Override
    @Transactional
    public List<Labor> findLaborByName(String laborDescription){

        return laborDao.findLaborByName(laborDescription);
    }


    @Override
    @Transactional(readOnly = true)
    public Labor findLaborById(int id){


       return laborDao.findLaborById(id)
                    .orElseThrow(() -> new RuntimeException("Labor id not found"));

    }

    @Override
    @Transactional
    public int updateLaborTimeAndDescription(double timedLabor, String laborDescription, int id){

        return laborDao.updateLaborTimeAndDescription(timedLabor, laborDescription, id);

    }

    @Override
    @Transactional
    public int deleteLabor(int id){

        Optional<Labor> labor = laborDao.findLaborById(id);

        if(labor.isPresent()){
            return laborDao.deleteLabor(id);
        }

        throw new RuntimeException("Labor not found");
    }

}
