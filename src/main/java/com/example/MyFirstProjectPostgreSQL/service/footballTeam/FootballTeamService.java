package com.example.MyFirstProjectPostgreSQL.service.footballTeam;

import com.example.MyFirstProjectPostgreSQL.model.FootballTeamModel;

import java.util.List;

public interface FootballTeamService {
    List<FootballTeamModel> getAll();

    FootballTeamModel get(Long id);

    FootballTeamModel create(FootballTeamModel footballTeamModel);

    FootballTeamModel update(Long id, FootballTeamModel footballTeamModel);

    void delete(Long id);
}
