package com.caliber.flatbudget.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table
@Data
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
    User user;

    @OneToMany
    @JsonIgnore
    private List<Transaction> transactionList;

    @ManyToOne
    private Budget budget;

}
