package com.gini.iordache.dao;

import com.gini.iordache.entity.auto.Part;

public interface PartCountDao {
    int updateStockCount(int partId, int count);
}