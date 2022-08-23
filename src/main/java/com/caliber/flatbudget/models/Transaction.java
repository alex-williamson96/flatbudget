package com.caliber.flatbudget.models;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table
@Data
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
    private User user;

    @ManyToMany
    private List<Category> categoryList;

    @ManyToOne
    private Payee payee;

}
