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
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private Long budget;

    @Column
    private String currency = "USD";

    @Column
    private String currencyFormat = "before";

    @Column String dateFormat = "MM.DD.YYYY";

    @OneToMany
    @ToString.Exclude
    List<Account> accountList;

    @OneToMany
    @ToString.Exclude
    List<Budget> budgetList;

    @JsonIgnore
    @OneToMany
    @ToString.Exclude
    List<Payee> payeeList;

    @JsonIgnore
    @OneToMany
    @ToString.Exclude
    List<Transaction> transactionList;

    @Column
    private LocalDateTime createdDate;

    @Column
    private LocalDateTime updatedDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return id != null && Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
