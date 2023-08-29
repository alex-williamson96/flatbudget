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
@Builder
public class Category {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @Column
    private Integer dollarAssigned = 0;

    @Column
    private Integer centsAssigned = 0;

    @Column
    private Integer dollarActivity = 0;

    @Column
    private Integer centsActivity = 0;

    @Column
    private Integer dollarAvailable = 0;

    @Column
    private Integer centsAvailable = 0;

    @Column
    private Integer mainOrder;

    @Column
    private Integer subOrder;

    @Column
    private Boolean isCreditCard;

    @Column
    private LocalDateTime createdDate;

    @Column
    private LocalDateTime updatedDate;

    @ManyToOne
    private User user;

    @ManyToMany
    @ToString.Exclude
    private List<Transaction> transactionList;

    @ManyToOne
    private BudgetTable budgetTable;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Category category = (Category) o;
        return id != null && Objects.equals(id, category.id);
    }
}
