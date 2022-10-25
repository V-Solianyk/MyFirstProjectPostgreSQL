package com.example.MyFirstProjectPostgreSQL.service.footballer;

import com.example.MyFirstProjectPostgreSQL.dto.FootballerDTO;
import com.example.MyFirstProjectPostgreSQL.entity.FootballTeam;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FootballerService {
    List<FootballerDTO> getAll();

    List<FootballerDTO> getAllByAge(Integer age, Pageable pageable);

    List<FootballerDTO> getAllByOverallRating(Integer overallRating, Pageable pageable);

    List<FootballerDTO> getAllByWorkingLegIsRight(Boolean workingLegIsRight, Pageable pageable);

    List<FootballerDTO> getAllByWorkingLegIsRightAndAge(Boolean workingLegIsRight, Integer age, Pageable pageable);

    List<FootballerDTO> getAllByFootballTeam(FootballTeam footballTeam, Pageable pageable);

    FootballerDTO get(Long id);

    FootballerDTO create(FootballerDTO footballerDTO);

    FootballerDTO update(Long id, FootballerDTO footballerDTO);

    void delete(Long id);
}
