package com.gini.iordache.services;

import com.gini.iordache.entity.auto.Part;
import com.gini.iordache.entity.auto.PartCount;
import org.springframework.transaction.annotation.Transactional;

public interface PartService {
    @Transactional
    void addPart(Part part);
}
