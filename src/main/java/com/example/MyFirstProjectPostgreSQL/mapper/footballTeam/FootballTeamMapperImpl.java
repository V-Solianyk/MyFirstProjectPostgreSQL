package com.example.MyFirstProjectPostgreSQL.mapper.footballTeam;

import com.example.MyFirstProjectPostgreSQL.entity.FootballTeam;
import com.example.MyFirstProjectPostgreSQL.model.FootballTeamModel;
import org.springframework.stereotype.Component;

@Component
public class FootballTeamMapperImpl implements FootballTeamMapper {
    @Override
    public FootballTeamModel footballTeamToFootballTeamModel(FootballTeam footballTeam) {
        FootballTeamModel footballTeamModel = new FootballTeamModel();
        footballTeamModel.setTeamName(footballTeam.getTeamName());
        footballTeamModel.setTeamBudget(footballTeam.getTeamBudget());
        footballTeamModel.setYearOfFoundation(footballTeam.getYearOfFoundation());
        footballTeamModel.setFootballers(footballTeam.getFootballers());

        return footballTeamModel;
    }

    @Override
    public FootballTeam footballTeamModelToFootballTeam(FootballTeamModel footballTeamModel) {
        FootballTeam footballTeam = new FootballTeam();
        footballTeam.setTeamName(footballTeamModel.getTeamName());
        footballTeam.setTeamBudget(footballTeamModel.getTeamBudget());
        footballTeam.setYearOfFoundation(footballTeamModel.getYearOfFoundation());
        footballTeam.setFootballers(footballTeamModel.getFootballers());

        return footballTeam;
    }
}
