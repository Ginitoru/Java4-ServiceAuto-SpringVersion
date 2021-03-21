package com.gini.iordache.entity.order;

import com.gini.iordache.entity.auto.Part;
import com.gini.iordache.entity.auto.Vehicle;
import com.gini.iordache.entity.clients.Client;
import com.gini.iordache.entity.labor.Labor;
import com.gini.iordache.entity.user.User;
import com.gini.iordache.utility.OrderStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class ServiceOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private double totalPrice;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private CarProblems carProblems;

    @ManyToOne
    private User user;

    @ManyToOne
    private Client client;

    @ManyToOne
    private Vehicle vehicle;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @OneToMany(mappedBy = "serviceOrder")
    private List<PartServiceOrder> parts = new ArrayList<>();


    @OneToMany(mappedBy = "serviceOrder")
    private List<LaborServiceOrder> labors = new ArrayList<>();






    public static class Builder{

        private ServiceOrder serviceOrder = new ServiceOrder();


        public Builder withId(int id){
            serviceOrder.id = id;
            return this;
        }


        public Builder withTotalPrice(double totalPrice){
            serviceOrder.totalPrice = totalPrice;
            return this;
        }

        public Builder withCarProblems(CarProblems carProblems){
            serviceOrder.carProblems = carProblems;
            return this;
        }

        public Builder withUser(User user){
            serviceOrder.user = user;
            return this;
        }

        public Builder withClient(Client client){
            serviceOrder.client = client;
            return this;
        }

        public Builder withVehicle(Vehicle vehicle){
            serviceOrder.vehicle = vehicle;
            return this;
        }


        public Builder withOrderStatus(OrderStatus orderStatus){
            serviceOrder.orderStatus = orderStatus;
            return this;
        }

        public Builder withPart(List<PartServiceOrder> parts){
            serviceOrder.parts = parts;
            return this;
        }



        public Builder withLabors(List<LaborServiceOrder> labors){
            serviceOrder.labors = labors;
            return this;
        }



        public ServiceOrder build(){
            return serviceOrder;
        }

    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServiceOrder that = (ServiceOrder) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
