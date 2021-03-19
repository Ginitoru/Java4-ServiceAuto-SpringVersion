package com.gini.iordache.entity.clients;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Company extends Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String cui;
    private String name;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return Objects.equals(cui, company.cui);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cui);
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", cui='" + cui + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
