package com.gini.iordache.dao.iterfaces;

import com.gini.iordache.entity.clients.Company;

import java.util.List;
import java.util.Optional;

public interface CompanyDao {
    void createCompany(Company company);

    Optional<Company> findCompanyByCui(String cui);

    List<Company> findCompanyByName(String companyName);
}
