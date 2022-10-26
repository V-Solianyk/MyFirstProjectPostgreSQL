package com.example.MyFirstProjectPostgreSQL.mapper.footballTeam;

import com.example.MyFirstProjectPostgreSQL.entity.FootballTeam;
import com.example.MyFirstProjectPostgreSQL.dto.FootballTeamDTO;
import org.springframework.stereotype.Component;

@Component
public class FootballTeamMapperImpl implements FootballTeamMapper {
    @Override
    public FootballTeamDTO footballTeamToFootballTeamDTO(FootballTeam footballTeam) {
        FootballTeamDTO footballTeamDTO = new FootballTeamDTO();
        footballTeamDTO.setTeamName(footballTeam.getTeamName());
        footballTeamDTO.setTeamBudget(footballTeam.getTeamBudget());
        footballTeamDTO.setYearOfFoundation(footballTeam.getYearOfFoundation());

        return footballTeamDTO;
    }

    @Override
    public FootballTeam footballTeamDTOToFootballTeam(FootballTeamDTO footballTeamDTO) {
        FootballTeam footballTeam = new FootballTeam();
        footballTeam.setTeamName(footballTeamDTO.getTeamName());
        footballTeam.setTeamBudget(footballTeamDTO.getTeamBudget());
        footballTeam.setYearOfFoundation(footballTeamDTO.getYearOfFoundation());

        return footballTeam;
    }
}
