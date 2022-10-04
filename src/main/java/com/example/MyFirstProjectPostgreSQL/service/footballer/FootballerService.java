package com.example.MyFirstProjectPostgreSQL.service.footballer;

import com.example.MyFirstProjectPostgreSQL.dto.FootballerDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FootballerService {
    List<FootballerDTO> getAll();

    List<FootballerDTO> getAllByAge(Integer age, Pageable pageable);

    List<FootballerDTO> getAllByOverallRating(Integer overallRating, Pageable pageable);

    List<FootballerDTO> getAllByWorkingLeg(String leg, Pageable pageable);

    List<FootballerDTO> getAllByWorkingLegAndAge(String leg, Integer age, Pageable pageable);

    List<FootballerDTO> getAllByFootballTeam(String footballTeam, Pageable pageable);

    FootballerDTO get(Long id);

    FootballerDTO create(FootballerDTO footballerModel);

    FootballerDTO update(Long id, FootballerDTO footballerModel);

    void delete(Long id);
}
