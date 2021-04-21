package com.gini.iordache.dao.impl.client;

import com.gini.iordache.dao.iterfaces.CompanyDao;
import com.gini.iordache.entity.clients.Company;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Transactional(propagation = Propagation.MANDATORY)
@Repository
public class CompanyDaoImpl implements CompanyDao {

    private final EntityManager entityManager;

    @Override
    public void createCompany(Company company){
        entityManager.persist(company);

    }


    @Override
    public Optional<Company> findCompanyByCui(String cui){

        String jpql = "SELECT c FROM Company c WHERE c.cui =: cui";

        return entityManager.createQuery(jpql, Company.class)
                                .setParameter("cui", cui)
                                .getResultStream()
                                .findFirst();
    }


    @Override
    public List<Company> findCompanyByName(String companyName){

        String jpql = "SELECT c FROM Company c WHERE c.name =: companyName";

        return entityManager.createQuery(jpql, Company.class)
                                .setParameter("companyName", companyName)
                                .getResultList();

    }
}
