package com.gini.iordache.entity.auto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class PartCount {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private int stockCount;

    @OneToOne
    private Part part;

}
