package com.gini.iordache.entity.labor;

import com.gini.iordache.utility.LaborCategory;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@NoArgsConstructor
@Getter
@Setter
@Entity
public class Labor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String laborDescription;
    private double timedLabor;


    @Enumerated(EnumType.STRING)
    private LaborCategory category;


}
