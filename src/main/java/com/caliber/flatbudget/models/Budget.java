package com.caliber.flatbudget.models;


import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table
@Data
public class Budget {

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany
    List<Category> categoryList;

    @Column
    private LocalDateTime createdDate;

    @Column
    private LocalDateTime updatedDate;

    @ManyToOne
    User user;
}
