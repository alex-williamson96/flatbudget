package com.caliber.flatbudget.models;


import lombok.*;
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

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany
    @ToString.Exclude
    List<Category> categoryList;

    @Column
    private LocalDateTime createdDate;

    @Column
    private LocalDateTime updatedDate;

    @ManyToOne
    User user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Budget budget = (Budget) o;
        return id != null && Objects.equals(id, budget.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
