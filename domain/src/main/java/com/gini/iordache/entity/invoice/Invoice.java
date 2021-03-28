package com.gini.iordache.entity.invoice;

import com.gini.iordache.entity.order.ServiceOrder;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Invoice {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String invoiceNumber;

    @Lob
    private byte[] invoice;

    @OneToOne(fetch = FetchType.LAZY)
    private ServiceOrder serviceOrder;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Invoice invoice = (Invoice) o;
        return id == invoice.id && Objects.equals(invoiceNumber, invoice.invoiceNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, invoiceNumber);
    }


    @Override
    public String toString() {
        return "Invoice{" +
                "id=" + id +
                ", number='" + invoiceNumber + '\'' +
                '}';
    }
}
