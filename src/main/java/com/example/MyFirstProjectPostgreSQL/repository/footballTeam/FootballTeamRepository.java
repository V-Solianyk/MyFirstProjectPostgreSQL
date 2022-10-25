package com.example.MyFirstProjectPostgreSQL.repository.footballTeam;

import com.example.MyFirstProjectPostgreSQL.entity.FootballTeam;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FootballTeamRepository extends JpaRepository<FootballTeam, Long> {
    List<FootballTeam> findAllByTeamBudget(Integer teamBudget, Pageable pageable);

    List<FootballTeam> findAllByYearOfFoundation(Integer yearOfFoundation, Pageable pageable);
}
