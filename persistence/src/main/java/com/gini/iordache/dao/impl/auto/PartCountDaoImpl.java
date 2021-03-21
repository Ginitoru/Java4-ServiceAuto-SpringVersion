package com.gini.iordache.dao.impl.auto;

import com.gini.iordache.dao.iterfaces.PartCountDao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@AllArgsConstructor
@Getter
@Setter
@Transactional(propagation = Propagation.MANDATORY)
@Repository
public class PartCountDaoImpl implements PartCountDao {

    private final EntityManager entityManager;


    @Override
    public int updateStockCount(int partId, int count){

        String jpql = "UPDATE PartCount p SET p.stockCount =: stockCount WHERE p.part.id =: id";



        int x= entityManager.createQuery(jpql)
                                .setParameter("stockCount", count)
                                .setParameter("id", partId)
                                .executeUpdate();

        System.out.println( "yyyyyyyyyyyyyyyyyyyy" + "UPDATE: " + x);
        return x;
    }


}
