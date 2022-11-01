package com.example.MyFirstProjectPostgreSQL.mapper.teamBudget;

import com.example.MyFirstProjectPostgreSQL.dto.TeamBudgetDTO;
import com.example.MyFirstProjectPostgreSQL.entity.TeamBudget;

public interface TeamBudgetMapper {
    TeamBudget teamBudgetDTOToTeamBudget(TeamBudgetDTO teamBudgetDTO);
}
