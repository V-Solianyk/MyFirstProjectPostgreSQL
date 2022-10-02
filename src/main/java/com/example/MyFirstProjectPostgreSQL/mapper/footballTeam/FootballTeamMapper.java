package com.example.MyFirstProjectPostgreSQL.mapper.footballTeam;

import com.example.MyFirstProjectPostgreSQL.entity.FootballTeam;
import com.example.MyFirstProjectPostgreSQL.model.FootballTeamModel;

public interface FootballTeamMapper {
    FootballTeamModel footballTeamToFootballTeamModel(FootballTeam footballTeam);

    FootballTeam footballTeamModelToFootballTeam(FootballTeamModel footballTeamModel);
}
