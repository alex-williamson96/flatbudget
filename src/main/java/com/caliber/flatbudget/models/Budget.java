package com.caliber.flatbudget.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Budget {

    @Id
    @GeneratedValue
    private Long budgetId;

    @Column
    private String name;

    @ManyToOne
    @JsonIgnore
    private User user;

    @OneToMany
    @ToString.Exclude
    private List<BudgetTable> budgetTableList = new ArrayList<>();

    @Column
    private LocalDateTime createdDate;

    @Column
    private LocalDateTime updatedDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Budget budget = (Budget) o;
        return budgetId != null && Objects.equals(budgetId, budget.budgetId);
    }
}
