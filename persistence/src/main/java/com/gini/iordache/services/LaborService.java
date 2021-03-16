package com.gini.iordache.services;

import com.gini.iordache.entity.labor.Labor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface LaborService {
    @Transactional
    void createLabor(Labor labor);

    @Transactional(readOnly = true)
    List<Labor> findAllLabors();

    @Transactional(readOnly = true)
    List<Labor> findLaborByName(String laborDescription);

    @Transactional(readOnly = true)
    Labor findLaborById(int id);

    @Transactional
    int updateLaborTimeAndDescription(double timedLabor, String laborDescription, int id);

    @Transactional
    int deleteLabor(int id);
}
