package com.caliber.flatbudget.models;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @Column
    private LocalDate transactionDate;

    @Column
    private String note;

    @Column
    private Integer dollar;

    @Column
    private Integer cents;

    @Column
    private Boolean isOutflow;

    @Column
    private Boolean isPending;

    @Column
    private LocalDateTime createdDate;

    @Column
    private LocalDateTime updatedDate;

    @ManyToOne
    private Account account;

    @ManyToOne
    private UserProfile userProfile;

    @ManyToMany
    @ToString.Exclude
    private List<Category> categoryList;

    @ManyToOne
    private Payee payee;

    @ManyToOne
    private Budget budget;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Transaction that = (Transaction) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
