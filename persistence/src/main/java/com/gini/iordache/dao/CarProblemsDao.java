package com.gini.iordache.dao;

import com.gini.iordache.entity.auto.CarProblems;

import java.util.List;

public interface CarProblemsDao {

    void createCarProblems(CarProblems carProblems);

    List<CarProblemsDao> carProblemsList();
}
