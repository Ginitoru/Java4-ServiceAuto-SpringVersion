package com.gini.iordache.cache;

import com.gini.iordache.entity.auto.Part;
import com.gini.iordache.entity.auto.Vehicle;
import com.gini.iordache.entity.clients.Company;
import com.gini.iordache.entity.clients.Person;
import com.gini.iordache.entity.labor.Labor;
import com.gini.iordache.entity.order.LaborOrder;
import com.gini.iordache.entity.order.ServiceOrder;

import java.util.List;
import java.util.Map;

public interface MiniCache {
    ServiceOrder loadCompleteServiceOrderById(int id);

    ServiceOrder getCompleteServiceOrder();

    void loadLaborsOrder();

    List<LaborOrder> retrieveLaborFromOrder();

    void retrieveLabors(String laborDescription);

    List<Labor> retrieveLabors();

    Part findPartByPartNumber(String partNumber);

    Vehicle findVehicleBySerialNumber(String serialNumber);

    Vehicle retrieveVehicle();

    Vehicle getEmptyVehicle();

    Person findPersonByCnp(String cnp);

    Person loadEmptyPerson();

    Person retrievePerson();

    Company findCompanyByCui(String cui);

    Company retrieveCompany();

    Company loadEmptyCompany();

    Part retrievePart();

    void resetCompanySearch();

    void resetPersonSearch();

    void resetCarSearch();

    Map<String, ServiceOrder> getOrder();

    Map<String, List<Labor>> getLabors();

    Map<String, List<LaborOrder>> getOrderLabors();

    Map<String, Part> getParts();

    Map<String, Vehicle> getVehicle();

    Map<String, Person> getPerson();

    Map<String, Company> getCompany();
}
