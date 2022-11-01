package com.example.MyFirstProjectPostgreSQL.service.footballTeam;

import com.example.MyFirstProjectPostgreSQL.dto.FootballTeamDTO;
import com.example.MyFirstProjectPostgreSQL.dto.TeamBudgetDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FootballTeamService {
    List<FootballTeamDTO> getAll();

    FootballTeamDTO get(Long id);

    List<FootballTeamDTO> getAllByYearOfFoundation(int yearOfFoundation, Pageable pageable);

    FootballTeamDTO create(FootballTeamDTO footballTeamDTO);

    FootballTeamDTO update(Long id, FootballTeamDTO footballTeamDTO);

    void delete(Long id);

    TeamBudgetDTO createTeamBudget(TeamBudgetDTO teamBudgetDTO);

    TeamBudgetDTO updateTeamBudget(Long id, TeamBudgetDTO teamBudgetDTO);
}
