package com.gini.iordache.services.impl.auto;

import com.gini.errors.auto.VehicleAlreadyExistsException;
import com.gini.iordache.dao.iterfaces.VehicleDao;
import com.gini.iordache.entity.auto.Vehicle;
import com.gini.iordache.services.interfaces.VehicleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@AllArgsConstructor
@Service
public class VehicleServiceImpl implements VehicleService {

    private final VehicleDao vehicleDao;


    @Override
    @Transactional
    public void createVehicle(Vehicle vehicle){

        Optional<Vehicle> optVehicle = vehicleDao.findVehicleBySerialNumber(vehicle.getSerialNumber());


        if(optVehicle.isEmpty()){
            vehicleDao.createVehicle(vehicle);
            return;
        }


        throw new VehicleAlreadyExistsException("Vehicle already exists");
    }


    @Override
    @Transactional
    public Vehicle findVehicleBySerialNumber(String serialNumber){

       return  vehicleDao.findVehicleBySerialNumber(serialNumber)
                                 .orElseThrow(() -> new RuntimeException("Vehicle not found"));

    }


}
