package com.caliber.flatbudget.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Account {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @Column
    private Integer dollar;

    @Column
    private Integer cents;

    @Column
    private Boolean onBudget;

    @Column
    private Integer orderPosition;

    @Column
    private LocalDateTime createdDate;

    @Column
    private LocalDateTime updatedDate;

    @ManyToOne
    UserProfile userProfile;

    @OneToMany
    @JsonIgnore
    @ToString.Exclude
    private List<Transaction> transactionList;

    @ManyToOne
    private Budget budget;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Account account = (Account) o;
        return id != null && Objects.equals(id, account.id);
    }
}
