package com.gini.iordache.entity.clients;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.util.Objects;

@NoArgsConstructor
@Getter
@Setter
@Embeddable
public class Adress {

    private String streetNumber;
    private String streetName;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Adress adress = (Adress) o;
        return Objects.equals(streetNumber, adress.streetNumber) && Objects.equals(streetName, adress.streetName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(streetNumber, streetName);
    }
}
