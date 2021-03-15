package com.gini.iordache.services;

import com.gini.iordache.entity.labor.Labor;
import org.springframework.transaction.annotation.Transactional;

public interface LaborService {
    @Transactional
    void createLabor(Labor labor);
}
