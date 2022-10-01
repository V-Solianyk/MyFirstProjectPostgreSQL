package com.example.MyFirstProjectPostgreSQL.repository;

import com.example.MyFirstProjectPostgreSQL.entity.Footballer;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface FootballerRepository extends PagingAndSortingRepository<Footballer, Long> {
    List<Footballer> findAllByAge(Integer age, Pageable pageable);

    List<Footballer> findAllByWorkingLeg(String workingLeg, Pageable pageable);

    List<Footballer> findAllByOverallRating(Integer age, Pageable pageable);

    List<Footballer> findAllByWorkingLegAndAge(String workingLeg, Integer age, Pageable pageable);
}
