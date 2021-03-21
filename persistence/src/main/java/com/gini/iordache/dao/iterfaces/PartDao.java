package com.gini.iordache.dao.iterfaces;

import com.gini.iordache.entity.auto.Part;

import java.util.Optional;

public interface PartDao {

     void createPart(Part part);


    Optional<Part> findPartByName(String partName);

    Optional<Part> findPartByPartNumber(String partNumber);


    int updatePartCount(int count, String partNumber);

    int updatePartCountAndPrice(int increment, double price, String partNumber);

    int decreasePartCount(int decrement, String partNumber);
}
