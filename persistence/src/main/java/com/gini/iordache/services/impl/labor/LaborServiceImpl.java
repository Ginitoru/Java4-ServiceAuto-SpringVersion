package com.gini.iordache.services.impl.labor;

import com.gini.iordache.dao.LaborDao;
import com.gini.iordache.entity.labor.Labor;
import com.gini.iordache.services.LaborService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class LaborServiceImpl implements LaborService {

    private final LaborDao laborDao;

    @Override
    @Transactional
    public void createLabor(Labor labor){
        laborDao.createLabor(labor);
    }





}
