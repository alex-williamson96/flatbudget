package com.caliber.flatbudget.models;

import com.caliber.flatbudget.models.security.ERole;
import com.caliber.flatbudget.models.security.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.Hibernate;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "user_profile", uniqueConstraints = {
        @UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "email")
})
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {


    @Id
    @GeneratedValue
    private Long userId;

    @Column
    private String username;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String email;

    @Column
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Column
    private Long activeBudget;

    @Column
    private String currency = "USD";

    @Column
    private String currencyFormat = "before";

    @Column
    String dateFormat = "MM.DD.YYYY";

    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    List<Budget> budgetList = new ArrayList<>();

    @JsonIgnore
    @OneToMany
    @ToString.Exclude
    List<Payee> payeeList;

    @JsonIgnore
    @OneToMany(orphanRemoval = true)
    @ToString.Exclude
    List<Transaction> transactionList;

    @Column
    private LocalDateTime createdDate;

    @Column
    private LocalDateTime updatedDate;

    @Column
    private Boolean enabled;

    @Transient
    private List<String> stringRoles;

//    @Enumerated(EnumType.STRING)
//    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
//    @Column(name = "role")
//    @ElementCollection(targetClass = ERole.class)
//    private Set<ERole> roles = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    @ToString.Exclude
    private Set<Role> roles = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return userId != null && Objects.equals(userId, user.userId);
    }
}
