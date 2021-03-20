package com.gini.iordache.dto;

import com.gini.iordache.utility.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@AllArgsConstructor
@Getter
@Setter
public class ServiceOrderIdAndStatusDto {

    private int id;
    private OrderStatus orderStatus;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServiceOrderIdAndStatusDto that = (ServiceOrderIdAndStatusDto) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "ServiceOrderIdAndCategoryDto{" +
                "id=" + id +
                ", orderStatus=" + orderStatus +
                '}';
    }
}