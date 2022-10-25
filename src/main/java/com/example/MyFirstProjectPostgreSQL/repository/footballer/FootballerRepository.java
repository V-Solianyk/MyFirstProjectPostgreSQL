package com.example.MyFirstProjectPostgreSQL.repository.footballer;

import com.example.MyFirstProjectPostgreSQL.entity.FootballTeam;
import com.example.MyFirstProjectPostgreSQL.entity.Footballer;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FootballerRepository extends JpaRepository<Footballer, Long> {
    List<Footballer> findAllByAge(Integer age, Pageable pageable);

    List<Footballer> findAllByWorkingLegIsRight(Boolean workingLegIsRight, Pageable pageable);

    List<Footballer> findAllByOverallRating(Integer age, Pageable pageable);

    List<Footballer> findAllByWorkingLegIsRightAndAge(Boolean workingLegIsRight, Integer age, Pageable pageable);

    List<Footballer> findAllByFootballTeam(FootballTeam footballTeam, Pageable pageable);
}
