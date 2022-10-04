package com.example.MyFirstProjectPostgreSQL.mapper.footballTeam;

import com.example.MyFirstProjectPostgreSQL.entity.FootballTeam;
import com.example.MyFirstProjectPostgreSQL.dto.FootballTeamDTO;

public interface FootballTeamMapper {
    FootballTeamDTO footballTeamToFootballTeamDTO(FootballTeam footballTeam);

    FootballTeam footballTeamDTOToFootballTeam(FootballTeamDTO footballTeamDTO);
}
