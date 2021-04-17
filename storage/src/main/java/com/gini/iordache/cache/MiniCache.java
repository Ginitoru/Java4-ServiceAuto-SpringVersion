package com.gini.iordache.cache;

import com.gini.iordache.entity.auto.Part;
import com.gini.iordache.entity.auto.Vehicle;
import com.gini.iordache.entity.clients.Company;
import com.gini.iordache.entity.clients.Person;
import com.gini.iordache.entity.labor.Labor;
import com.gini.iordache.entity.order.LaborOrder;
import com.gini.iordache.entity.order.ServiceOrder;

import java.util.List;

public interface MiniCache {
    ServiceOrder loadCompleteServiceOrderById(int id);

    ServiceOrder getCompleteServiceOrder();

    void loadLaborsOrder();

    List<LaborOrder> getLaborFromOrder();

    void loadLabors(String laborDescription);

    List<Labor> getLabors();

    Part findPartByPartNumber(String partNumber);

    Vehicle findVehicleBySerialNumber(String serialNumber);

    Vehicle getVehicle();

    Vehicle getEmptyVehicle();

    Person findPersonByCnp(String cnp);

    Person getEmptyPerson();

    Person getPerson();

    Company findCompanyByCui(String cui);

    Company getCompany();

    Company getEmptyCompany();

    Part getPart();

    void resetCompanySearch();

    void resetPersonSearch();

    void resetCarSearch();
}
