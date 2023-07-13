package com.caliber.flatbudget.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserProfile {


    @Id
    @GeneratedValue
    private Long userId;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private Long activeBudget;

    @Column
    private String currency = "USD";

    @Column
    private String currencyFormat = "before";

    @Column String dateFormat = "MM.DD.YYYY";

    @OneToMany(mappedBy = "userProfile")
    @ToString.Exclude
    List<Budget> budgetList = new ArrayList<>();

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
        UserProfile userProfile = (UserProfile) o;
        return userId != null && Objects.equals(userId, userProfile.userId);
    }
}
