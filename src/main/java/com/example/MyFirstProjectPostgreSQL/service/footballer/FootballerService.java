package com.example.MyFirstProjectPostgreSQL.service.footballer;

import com.example.MyFirstProjectPostgreSQL.dto.FootballerDTO;
import com.example.MyFirstProjectPostgreSQL.entity.FootballTeam;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FootballerService {
    List<FootballerDTO> getAll();

    List<FootballerDTO> getAllByAge(int age, Pageable pageable);

    List<FootballerDTO> getAllByOverallRating(int overallRating, Pageable pageable);

    List<FootballerDTO> getAllBySurnameContainsIgnoreCase(String keyword, Pageable pageable);

    List<FootballerDTO> getAllByWorkingLegIsRight(Boolean workingLegIsRight, Pageable pageable);

    List<FootballerDTO> getAllByWorkingLegIsRightAndAge(Boolean workingLegIsRight, int age, Pageable pageable);

    List<FootballerDTO> getAllByFootballTeamId(Long footballTeamId, Pageable pageable);

    FootballerDTO get(Long id);

    FootballerDTO create(FootballerDTO footballerDTO);

    FootballerDTO update(Long id, FootballerDTO footballerDTO);

    void delete(Long id);
}
