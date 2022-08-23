package com.caliber.flatbudget.models;


import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table
@Data
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
    private Profile profile;

    @ManyToOne
    private Category presumedCategory;

    @ManyToOne
    private Category setCategory;

    @OneToMany
    private List<Transaction> transactionList;
}
