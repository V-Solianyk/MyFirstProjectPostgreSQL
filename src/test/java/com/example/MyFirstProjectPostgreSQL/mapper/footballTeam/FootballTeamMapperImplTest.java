package com.example.MyFirstProjectPostgreSQL.mapper.footballTeam;

import com.example.MyFirstProjectPostgreSQL.dto.FootballTeamDTO;
import com.example.MyFirstProjectPostgreSQL.entity.FootballTeam;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FootballTeamMapperImplTest {
    FootballTeamMapperImpl footballTeamMapper = new FootballTeamMapperImpl();

    @Test
    void FootballTeamToFootballTeamDTO() {
        FootballTeam footballTeam = new FootballTeam();
        footballTeam.setTeamName("Real Madrid");
        footballTeam.setYearOfFoundation(1894);

        FootballTeamDTO footballTeamDTO = footballTeamMapper.footballTeamToFootballTeamDTO(footballTeam);

        Assertions.assertEquals(footballTeam.getTeamName(), footballTeamDTO.getTeamName());
        Assertions.assertEquals(footballTeam.getYearOfFoundation(), footballTeamDTO.getYearOfFoundation());
    }

    @Test
    void FootballTeamDTOToFootballTeam() {
        FootballTeamDTO footballTeamDTO = new FootballTeamDTO();
        footballTeamDTO.setTeamName("FC Roma");
        footballTeamDTO.setYearOfFoundation(1888);

        FootballTeam footballTeam = footballTeamMapper.footballTeamDTOToFootballTeam(footballTeamDTO);

        Assertions.assertEquals(footballTeamDTO.getTeamName(), footballTeam.getTeamName());
        Assertions.assertEquals(footballTeamDTO.getYearOfFoundation(), footballTeam.getYearOfFoundation());
    }
}
