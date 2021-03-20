package com.gini.iordache.dto;

import com.gini.iordache.utility.OrderStatus;
import lombok.AllArgsConstructor;

import java.util.Objects;

@AllArgsConstructor
public class ServiceOrderIdAndCategoryDto {

    private int id;
    private OrderStatus orderStatus;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServiceOrderIdAndCategoryDto that = (ServiceOrderIdAndCategoryDto) o;
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