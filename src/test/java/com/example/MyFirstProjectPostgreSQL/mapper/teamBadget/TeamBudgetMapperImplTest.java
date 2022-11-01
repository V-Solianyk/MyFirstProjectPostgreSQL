package com.example.MyFirstProjectPostgreSQL.mapper.teamBadget;

import com.example.MyFirstProjectPostgreSQL.dto.TeamBudgetDTO;
import com.example.MyFirstProjectPostgreSQL.entity.TeamBudget;
import com.example.MyFirstProjectPostgreSQL.mapper.teamBudget.TeamBudgetMapperImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class TeamBudgetMapperImplTest {
    TeamBudgetMapperImpl teamBudgetMapper = new TeamBudgetMapperImpl();

    @Test
    void teamBudgetDTOToTeamBudget() {
        TeamBudgetDTO teamBudgetDTO = new TeamBudgetDTO();
        teamBudgetDTO.setValueTeamBudget(new BigDecimal(999999));
        teamBudgetDTO.setCurrencyCode("USD");

        TeamBudget teamBudget = teamBudgetMapper.teamBudgetDTOToTeamBudget(teamBudgetDTO);

        Assertions.assertEquals(teamBudgetDTO.getValueTeamBudget(), teamBudget.getValueTeamBudget());
        Assertions.assertEquals(teamBudgetDTO.getCurrencyCode(), teamBudget.getCurrency().getCurrencyCode());
    }
}
