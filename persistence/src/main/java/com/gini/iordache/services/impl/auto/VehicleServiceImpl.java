package com.gini.iordache.services.impl.auto;

import com.gini.errors.auto.VehicleAlreadyExists;
import com.gini.iordache.dao.VehicleDao;
import com.gini.iordache.entity.auto.Vehicle;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

@AllArgsConstructor
@Service
public class VehicleServiceImpl implements com.gini.iordache.services.VehicleService {

    private final VehicleDao vehicleDao;


    @Override
    @Transactional
    public void createVehicle(Vehicle vehicle){

        Optional<Vehicle> optVehicle = vehicleDao.findVehicleBySerialNumber(vehicle.getSerialNumber());


        if(optVehicle.isEmpty()){
            vehicleDao.createVehicle(vehicle);
            return;
        }


        throw new VehicleAlreadyExists("Vehicle already exists");


    }


}
