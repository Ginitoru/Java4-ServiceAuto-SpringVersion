package com.gini.iordache.dao.iterfaces;

import com.gini.iordache.entity.labor.Labor;

import java.util.List;
import java.util.Optional;

public interface LaborDao {
    void createLabor(Labor labor);

    List<Labor> findLaborByName(String laborDescription);

    int updateLaborTimeAndDescription(double timedLabor, String laborDescription, int id);

    List<Labor> findAllLabors();

    Optional<Labor> findLaborById(int id);

    int deleteLabor(int id);
}
