package com.gini.iordache.entity.auto;


import com.gini.iordache.entity.order.ServiceOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "vehicles")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotNull(message = "required")
    @Size(max = 30, message = "manufacturer name is to long")
    @Column(name = "car_manufacturer", nullable = false)
    private String carManufacturer;

    @NotNull(message = "required")
    @Size(max = 30, message = "model name is to long")
    @Column(name = "car_model", nullable = false)
    private String carModel;

    @Size(max = 17, message = "VIN lenght can't be more than {max} characters")
    @NotNull(message = "required")
    @Pattern(regexp = "^[0-9]*[a-zA-Z]+[a-zA-Z0-9]*$", message = "invalid VIN")
    @Column(name = "serial_number_or_vin", unique = true)
    private String serialNumber;

    @OneToMany(mappedBy = "vehicle")
    private Set<ServiceOrder> serviceOrders = new HashSet<>();


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return Objects.equals(serialNumber, vehicle.serialNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serialNumber);
    }


    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", carManufacturer='" + carManufacturer + '\'' +
                ", carModel='" + carModel + '\'' +
                ", serialNumber='" + serialNumber + '\'' +
                '}';
    }
}
