package com.caliber.flatbudget.models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
@Data
public class Category {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @Column
    private Integer dollarAssigned;

    @Column
    private Integer centsAssigned;

    @Column
    private Integer dollarActivity;

    @Column
    private Integer centsActivity;

    @Column
    private Integer dollarAvailable;

    @Column
    private Integer centsAvailable;

    @Column
    private Integer mainOrder;

    @Column
    private Integer subOrder;

    @ManyToOne
    private Profile profile;

    @ManyToMany
    List<Transaction> transactionList;
}
