package com.gini.iordache.services.interfaces;

import com.gini.iordache.entity.auto.Vehicle;
import org.springframework.transaction.annotation.Transactional;

public interface VehicleService {
    @Transactional
    void createVehicle(Vehicle vehicle);

    @Transactional
    Vehicle findVehicleBySerialNumber(String serialNumber);
}
