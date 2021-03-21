package com.gini.iordache.entity.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

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
    private LocalDateTime expiredAt;

    @OneToOne
    private User user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActivationToken that = (ActivationToken) o;
        return id == that.id && Objects.equals(token, that.token) && Objects.equals(createdAt, that.createdAt) && Objects.equals(activatedAt, that.activatedAt) && Objects.equals(expiredAt, that.expiredAt) && Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, token, createdAt, activatedAt, expiredAt, user);
    }
}
