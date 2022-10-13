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
public class Payee {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @Column
    private LocalDateTime createdDate;

    @Column
    private LocalDateTime updatedDate;

    @ManyToOne
    private UserProfile userProfile;

    @ManyToOne
    private Category presumedCategory;

    @ManyToOne
    private Category setCategory;

    @OneToMany
    @ToString.Exclude
    private List<Transaction> transactionList;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Payee payee = (Payee) o;
        return id != null && Objects.equals(id, payee.id);
    }
}
