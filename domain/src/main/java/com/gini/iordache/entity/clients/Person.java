package com.gini.iordache.entity.clients;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Objects;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Person extends Client{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotNull(message = "required")
    @Size(min = 13, max = 13, message = "has to have {max} digits")
    @Pattern(regexp = "[0-9]+", message = "only digits")
    private String cnp;

    @NotNull(message = "required")
    @Size(min = 5, message = "must have a minimum of {min} characters")
    private String firstName;

    @NotNull(message = "required")
    @Size(max = 50, message = "must have a maximum onf {max} characters")
    private String lastName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(cnp, person.cnp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cnp);
    }


    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", cnp='" + cnp + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
