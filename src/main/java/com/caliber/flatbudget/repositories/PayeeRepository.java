package com.caliber.flatbudget.repositories;

import com.caliber.flatbudget.models.Payee;
import com.caliber.flatbudget.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PayeeRepository extends JpaRepository<Payee, Long> {

    List<Payee> findAllByUser(User user);
}
