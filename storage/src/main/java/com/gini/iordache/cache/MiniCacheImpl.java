package com.gini.iordache.cache;

import com.gini.iordache.entity.auto.Part;
import com.gini.iordache.entity.auto.Vehicle;
import com.gini.iordache.entity.clients.Company;
import com.gini.iordache.entity.clients.Person;
import com.gini.iordache.entity.labor.Labor;
import com.gini.iordache.entity.order.LaborOrder;
import com.gini.iordache.entity.order.ServiceOrder;
import com.gini.iordache.services.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class MiniCacheImpl implements MiniCache {  //todo: de vazut cum inlocuiesc asta cu un cache


    private final OrderService serviceOrderService;
    private final LaborService laborService;
    private final PartService partService;
    private final VehicleService vehicleService;
    private final PersonService personService;
    private final CompanyService companyService;

    private static final Map<String, ServiceOrder> order = new HashMap<>();
    private final HashMap<String, List<Labor>> labors = new HashMap<>();
    private final HashMap<String, List<LaborOrder>> orderLabors = new HashMap<>();
    private final Map<String, Part> parts = new HashMap<>();
    private final Map<String, Vehicle> vehicle = new HashMap<>();
    private final Map<String, Person> person = new HashMap<>();
    private final Map<String, Company> company = new HashMap<>();

    @Autowired
    public MiniCacheImpl(OrderService serviceOrderService,
                         LaborService laborService,
                         PartService partService,
                         VehicleService vehicleService,
                         PersonService personService,
                         CompanyService companyService) {

        this.serviceOrderService = serviceOrderService;
        this.laborService = laborService;
        this.partService = partService;
        this.vehicleService = vehicleService;
        this.personService = personService;
        this.companyService = companyService;
    }


    @Override
    public ServiceOrder loadCompleteServiceOrderById(int id){

        var serviceOrder = serviceOrderService.findCompleteServiceOrderById(id);

        order.put(username(), serviceOrder);
        return order.get(username());
    }


    @Override
    public ServiceOrder getCompleteServiceOrder(){
        return order.get(username());
    }


    @Override
    public void loadLaborsOrder(){
        orderLabors.put(username(), serviceOrderService.
                                                 findAllLaborsInOrder(getCompleteServiceOrder().getId()));
    }


    @Override
    public List<LaborOrder> getLaborFromOrder(){
        return orderLabors.get(username());
    }


    @Override
    public void loadLabors(String laborDescription){
        labors.put(username(), laborService.findLaborByName(laborDescription));
    }


    @Override
    public List<Labor> getLabors(){
       return labors.get(username());
    }



    @Override
    public Part findPartByPartNumber(String partNumber){
        parts.put(username(),partService.findPartByPartNumber(partNumber));

        return parts.get(username());
    }


    @Override
    public Vehicle findVehicleBySerialNumber(String serialNumber){
        vehicle.put(username(), vehicleService.findVehicleBySerialNumber(serialNumber));
        return vehicle.get(username());
    }


    @Override
    public Vehicle getVehicle(){
        return vehicle.get(username());
    }


    @Override
    public Vehicle getEmptyVehicle(){
        vehicle.put(username(), new Vehicle());
        return vehicle.get(username());
    }


    @Override
    public Person findPersonByCnp(String cnp){
        person.put(username(), personService.findPersonByCnp(cnp));
        return person.get(username());
    }


    @Override
    public Person getEmptyPerson(){
        person.put(username(), new Person());
        return person.get(username());
    }

    @Override
    public Person getPerson(){
        return person.get(username());
    }


    @Override
    public Company findCompanyByCui(String cui){
        company.put(username(),companyService.findCompanyByCui(cui));
        return company.get(username());
    }


    @Override
    public Company getCompany(){
        return company.get(username());
    }


    @Override
    public Company getEmptyCompany(){
        company.put(username(), new Company());
        return company.get(username());
    }


    @Override
    public Part getPart(){
        return parts.get(username());
    }


    @Override
    public void resetCompanySearch(){
        company.put(username(), new Company());
    }


    @Override
    public void resetPersonSearch(){
        person.put(username(), new Person());
    }


    @Override
    public void resetCarSearch(){
        vehicle.put(username(), new Vehicle());
    }




    public String username(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }









}
