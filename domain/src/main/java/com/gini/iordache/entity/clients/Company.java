package com.gini.iordache.entity.clients;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Objects;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Company extends Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotNull(message = "required")
    @Size(max = 30, message = " must have a maximum of {max} characters ")
    @Pattern(regexp = "[0-9]+", message = "only digits")
    private String cui;

    @NotNull(message = "required")
    @Size(max = 100, message = " must have a maximum of {max} characters ")
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
