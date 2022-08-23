package com.caliber.flatbudget.repositories;

import com.caliber.flatbudget.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
