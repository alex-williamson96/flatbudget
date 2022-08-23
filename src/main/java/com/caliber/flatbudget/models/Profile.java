package com.caliber.flatbudget.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table
@Data
public class Profile {

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
    List<Account> accountList;

    @OneToMany
    List<Budget> budgetList;

    @JsonIgnore
    @OneToMany
    List<Payee> payeeList;

    @JsonIgnore
    @OneToMany
    List<Transaction> transactionList;

    @Column
    private LocalDateTime createdDate;

    @Column
    private LocalDateTime updatedDate;

}
