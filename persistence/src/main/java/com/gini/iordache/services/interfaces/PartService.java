package com.gini.iordache.services.interfaces;

import com.gini.iordache.entity.auto.Part;
import org.springframework.transaction.annotation.Transactional;

public interface PartService {
    @Transactional
    void addPart(Part part);

    @Transactional
    Part findPartByPartNumber(String partNumber);

    @Transactional
    int decreasePartCount(int decrement, String partNumber);
}
