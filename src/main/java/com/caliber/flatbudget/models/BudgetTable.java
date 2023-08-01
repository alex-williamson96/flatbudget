package com.caliber.flatbudget.models;

import com.caliber.flatbudget.models.user.UserProfile;
import lombok.*;

import javax.persistence.*;
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
    @GeneratedValue
    private Long id;

    @OneToMany
    @ToString.Exclude
    private List<Category> categoryList = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "userId")
    private UserProfile user;

    @ManyToOne
    private Budget budget;

    @Column
    private LocalDateTime month;

    @Column
    private LocalDateTime createdDate;

    @Column
    private LocalDateTime updatedDate;

    @Column
    private String notes;
}
