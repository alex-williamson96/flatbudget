package com.caliber.flatbudget.models.security;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table
@Getter
@Setter
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole name;

    @JsonCreator
    public Role() {
    }

    @JsonCreator
    public Role(ERole name) {
        this.name = name;
    }
}