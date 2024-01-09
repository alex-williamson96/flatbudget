package com.caliber.flatbudget.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.Hibernate;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
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

    @Column
    private String notes = "";

    @ManyToOne
    @JsonIgnore
    private User user;

    @ManyToMany
    @ToString.Exclude
    private List<Transaction> transactionList;

    @ManyToOne
    @JsonIgnore
    private BudgetTable budgetTable;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Category category = (Category) o;
        return id != null && Objects.equals(id, category.id);
    }
}
