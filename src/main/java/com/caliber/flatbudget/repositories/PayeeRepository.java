package com.caliber.flatbudget.repositories;

import com.caliber.flatbudget.models.Payee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PayeeRepository extends JpaRepository<Payee, Long> {
}
