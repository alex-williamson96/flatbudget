package com.caliber.flatbudget.repositories;

import com.caliber.flatbudget.models.Account;
import com.caliber.flatbudget.models.Budget;
import com.caliber.flatbudget.models.Transaction;
import com.caliber.flatbudget.models.user.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findAllByUserProfileAndBudget(UserProfile userProfile, Budget budget);

    List<Transaction> findAllByAccount(Account account);
}
