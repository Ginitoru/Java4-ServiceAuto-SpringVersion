package com.gini.iordache.dao.iterfaces;

import com.gini.iordache.entity.order.CarProblems;

import java.util.List;

public interface CarProblemsDao {

    void createCarProblems(CarProblems carProblems);

    List<CarProblemsDao> carProblemsList();
}
