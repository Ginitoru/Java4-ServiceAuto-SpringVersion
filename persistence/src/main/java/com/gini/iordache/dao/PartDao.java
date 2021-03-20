package com.gini.iordache.dao;

import com.gini.iordache.entity.auto.Part;

import java.util.Optional;

public interface PartDao {

     void createPart(Part part);


    Optional<Part> findPartByName(String partName);

    Optional<Part> findPartByPartNumber(String partNumber);

    int updatePartPrice(double price, String partNumber);

    int updatePartCount(int increment, String partNumber);

    int decreasePartCount(int decrement, String partNumber);
}
