package com.gini.iordache.services.interfaces;

import com.gini.iordache.entity.clients.Company;
import org.springframework.transaction.annotation.Transactional;

public interface CompanyService {
    @Transactional
    void createCompany(Company company);

    @Transactional(readOnly = true)
    Company findCompanyByCui(String cui);
}
