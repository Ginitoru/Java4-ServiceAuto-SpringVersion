package com.gini.iordache.entity.labor;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class PriceOfLabor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private  double mechanicalLabor;
    private  double bodyLabor;
    private  double electricalLabor;


}
