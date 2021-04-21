package com.gini.iordache.services.impl.client;

import com.gini.errors.client.CompanyAlreadyExistsException;
import com.gini.errors.client.CompanyNotFoundException;
import com.gini.iordache.dao.iterfaces.CompanyDao;
import com.gini.iordache.entity.clients.Company;
import com.gini.iordache.services.interfaces.CompanyService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@AllArgsConstructor
@Service
public class CompanyServiceImpl implements CompanyService {

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


    @Override
    @Transactional(readOnly = true)
    public Company findCompanyByCui(String cui){

        return companyDao.findCompanyByCui(cui)
                        .orElseThrow(() -> new CompanyNotFoundException("Company not found!"));

    }
}
