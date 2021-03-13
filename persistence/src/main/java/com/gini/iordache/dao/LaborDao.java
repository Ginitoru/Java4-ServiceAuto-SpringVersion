package com.gini.iordache.dao;

import com.gini.iordache.entity.labor.Labor;

import java.util.List;

public interface LaborDao {
    void createLabor(Labor labor);

    List<Labor> findLaborByName(String laborDescription);
}
