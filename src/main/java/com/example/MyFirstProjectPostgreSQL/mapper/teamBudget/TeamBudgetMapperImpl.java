package com.example.MyFirstProjectPostgreSQL.mapper.teamBudget;

import com.example.MyFirstProjectPostgreSQL.dto.TeamBudgetDTO;
import com.example.MyFirstProjectPostgreSQL.entity.TeamBudget;
import org.springframework.stereotype.Component;

import java.util.Currency;

@Component
public class TeamBudgetMapperImpl implements TeamBudgetMapper {

    @Override
    public TeamBudget teamBudgetDTOToTeamBudget(TeamBudgetDTO teamBudgetDTO) {
        TeamBudget teamBudget = new TeamBudget();
        teamBudget.setValueTeamBudget(teamBudgetDTO.getValueTeamBudget());
        teamBudget.setCurrency(Currency.getInstance(teamBudgetDTO.getCurrencyCode()));

        return teamBudget;
    }
}
