package com.caliber.flatbudget.models;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Budget {

    // acts as the rows of the budget

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @ManyToOne
    private UserProfile userProfile;

    @OneToMany
    @ToString.Exclude
    private List<Category> categoryList;

    @Column
    private LocalDateTime createdDate;

    @Column
    private LocalDateTime updatedDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Budget budget = (Budget) o;
        return id != null && Objects.equals(id, budget.id);
    }
}
