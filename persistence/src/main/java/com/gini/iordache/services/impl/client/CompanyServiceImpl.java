package com.gini.iordache.services.impl.client;

import com.gini.errors.client.CompanyAlreadyExistsException;
import com.gini.iordache.dao.CompanyDao;
import com.gini.iordache.entity.clients.Company;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@AllArgsConstructor
@Service
public class CompanyServiceImpl implements com.gini.iordache.services.CompanyService {

    private final CompanyDao companyDao;

    @Override
    @Transactional
    public void createCompany(Company company){

        Optional<Company> optCompany = companyDao
                                            .findCompanyByCui(company.getCui());


        if(optCompany.isEmpty()){
            companyDao.createCompany(company);
            return;
        }

        throw new CompanyAlreadyExistsException("Company Already exists");


    }


}
