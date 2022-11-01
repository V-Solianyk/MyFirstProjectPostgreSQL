package com.example.MyFirstProjectPostgreSQL.repository;

import com.example.MyFirstProjectPostgreSQL.entity.Footballer;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FootballerRepository extends JpaRepository<Footballer, Long> {
    List<Footballer> findAllByAge(int age, Pageable pageable);

    List<Footballer> findAllByWorkingLegIsRight(Boolean workingLegIsRight, Pageable pageable);

    List<Footballer> findAllByOverallRating(int age, Pageable pageable);

    List<Footballer> findAllBySurnameContainsIgnoreCase(String keyword, Pageable pageable);

    List<Footballer> findAllByWorkingLegIsRightAndAge(Boolean workingLegIsRight, int age, Pageable pageable);

    List<Footballer> findAllByFootballTeamId(Long footballTeamId, Pageable pageable);
}
