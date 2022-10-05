package com.caliber.flatbudget.models;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Category {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @Column
    private Integer dollarAssigned;

    @Column
    private Integer centsAssigned;

    @Column
    private Integer dollarActivity;

    @Column
    private Integer centsActivity;

    @Column
    private Integer dollarAvailable;

    @Column
    private Integer centsAvailable;

    @Column
    private Integer mainOrder;

    @Column
    private Integer subOrder;

    @ManyToOne
    User user;

    @ManyToMany
    @ToString.Exclude
    List<Transaction> transactionList;

    @ManyToOne
    Budget budget;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Category category = (Category) o;
        return id != null && Objects.equals(id, category.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
