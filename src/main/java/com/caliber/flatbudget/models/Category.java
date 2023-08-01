package com.caliber.flatbudget.models;

import com.caliber.flatbudget.models.user.UserProfile;
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

    @Column
    private Boolean isCreditCard;

    @Column
    private LocalDateTime createdDate;

    @Column
    private LocalDateTime updatedDate;

    @ManyToOne
    private UserProfile userProfile;

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
