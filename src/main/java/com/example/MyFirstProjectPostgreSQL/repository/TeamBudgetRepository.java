package com.example.MyFirstProjectPostgreSQL.repository;

import com.example.MyFirstProjectPostgreSQL.entity.TeamBudget;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamBudgetRepository extends JpaRepository<TeamBudget,Long> {
}
