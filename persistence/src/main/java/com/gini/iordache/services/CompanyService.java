package com.gini.iordache.services;

import com.gini.iordache.entity.clients.Company;
import org.springframework.transaction.annotation.Transactional;

public interface CompanyService {
    @Transactional
    void createCompany(Company company);
}
