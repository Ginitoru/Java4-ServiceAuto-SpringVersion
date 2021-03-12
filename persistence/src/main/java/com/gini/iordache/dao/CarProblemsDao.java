package com.gini.iordache.dao;

import java.util.List;

public interface CarProblemsDao {

    void createCarProblems(String carProblems);

    List<CarProblemsDao> carProblemsList();
}
