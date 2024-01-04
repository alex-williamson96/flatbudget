package com.caliber.flatbudget.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.Hibernate;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Transaction {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
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
    @JsonIgnore
    @ToString.Exclude
    private Account account;

    @ManyToOne
    @JsonIgnore
    @ToString.Exclude
    private User user;

    @ManyToMany
    @ToString.Exclude
    private List<Category> categoryList;

    @ManyToOne
    @ToString.Exclude
    private Payee payee;

    @ManyToOne
    @ToString.Exclude
    private Budget budget;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Transaction that = (Transaction) o;
        return id != null && Objects.equals(id, that.id);
    }
}
