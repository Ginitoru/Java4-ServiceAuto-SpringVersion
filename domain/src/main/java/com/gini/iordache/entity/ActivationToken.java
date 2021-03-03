package com.gini.iordache.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class ActivationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String token;
    private LocalDateTime createdAt;
    private LocalDateTime activatedAt;

    @OneToOne (mappedBy = "activationToken")
    private User user;

}
