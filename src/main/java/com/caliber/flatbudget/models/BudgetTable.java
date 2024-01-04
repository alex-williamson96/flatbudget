package com.caliber.flatbudget.models;

import lombok.*;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BudgetTable {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @OneToMany
    @ToString.Exclude
    private List<Category> categoryList = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    private Budget budget;

    @Column
    private String month;

    @Column
    private String year;

    @Column
    private LocalDateTime createdDate;

    @Column
    private LocalDateTime updatedDate;

    @Column
    private String notes;
}
