package com.caliber.flatbudget.models.security;

import com.caliber.flatbudget.models.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Getter
@Setter
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    @JoinColumn(referencedColumnName = "userId")
    private User user;

    @Column(nullable = false, unique = true)
    private String token;

    @Column(nullable = false)
    private Instant expiryDate;
}
