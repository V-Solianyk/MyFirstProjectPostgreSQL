package com.example.MyFirstProjectPostgreSQL.service.footballTeam;

import com.example.MyFirstProjectPostgreSQL.dto.FootballTeamDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FootballTeamService {
    List<FootballTeamDTO> getAll();

    FootballTeamDTO get(Long id);

    List<FootballTeamDTO> getAllByTeamBudget(Integer teamBudget, Pageable pageable);

    List<FootballTeamDTO> getAllByYearOfFoundation(Integer yearOfFoundation, Pageable pageable);

    FootballTeamDTO create(FootballTeamDTO footballTeamDTO);

    FootballTeamDTO update(Long id, FootballTeamDTO footballTeamDTO);

    void delete(Long id);
}
