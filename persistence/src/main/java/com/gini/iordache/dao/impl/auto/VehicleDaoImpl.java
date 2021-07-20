package com.gini.iordache.dao.impl.auto;

import com.gini.iordache.dao.iterfaces.VehicleDao;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import com.gini.iordache.entity.auto.Vehicle;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Optional;


@AllArgsConstructor
@Transactional(propagation = Propagation.MANDATORY)
@Repository
public class VehicleDaoImpl implements VehicleDao {

    private final EntityManager entityManager;



    @Override
    public void createVehicle(Vehicle vehicle){
        entityManager.persist(vehicle);
    }


    @Override
    public Optional<Vehicle> findVehicleBySerialNumber(String serialNumber){

        String jpql = "SELECT v FROM Vehicle v WHERE v.serialNumber =: serialNumber";

        return entityManager.createQuery(jpql, Vehicle.class)
                            .setParameter("serialNumber", serialNumber)
                            .getResultStream()
                            .findFirst();
    }
}
