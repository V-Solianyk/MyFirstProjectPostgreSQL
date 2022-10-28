package com.example.MyFirstProjectPostgreSQL.mapper.footballer;

import com.example.MyFirstProjectPostgreSQL.dto.FootballerDTO;
import com.example.MyFirstProjectPostgreSQL.entity.FootballTeam;
import com.example.MyFirstProjectPostgreSQL.entity.Footballer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FootballerMapperImplTest {
    FootballerMapperImpl footballerMapper = new FootballerMapperImpl();

    @Test
    void footballerToFootballerDTOWithoutFootballerTeamId() {
        Footballer footballer = new Footballer();
        footballer.setSurname("Shevchenko");
        footballer.setAge(38);
        footballer.setWorkingLegIsRight(true);
        footballer.setOverallRating(88);

        FootballerDTO footballerDTO = footballerMapper.footballerToFootballerDTO(footballer);

        Assertions.assertEquals(footballer.getSurname(), footballerDTO.getSurname());
        Assertions.assertEquals(footballer.getAge(), footballerDTO.getAge());
        Assertions.assertEquals(footballer.getWorkingLegIsRight(), footballerDTO.getWorkingLegIsRight());
        Assertions.assertEquals(footballer.getOverallRating(), footballerDTO.getOverallRating());
    }

    @Test
    void footballerToFootballerDTOWithFootballerTeamId() {
        FootballTeam footballTeam = new FootballTeam();
        footballTeam.setId(10L);

        Footballer footballer = new Footballer();
        footballer.setSurname("Shevchenko");
        footballer.setAge(38);
        footballer.setWorkingLegIsRight(true);
        footballer.setOverallRating(88);
        footballer.setFootballTeam(footballTeam);

        FootballerDTO footballerDTO = footballerMapper.footballerToFootballerDTO(footballer);

        Assertions.assertEquals(footballer.getSurname(), footballerDTO.getSurname());
        Assertions.assertEquals(footballer.getAge(), footballerDTO.getAge());
        Assertions.assertEquals(footballer.getWorkingLegIsRight(), footballerDTO.getWorkingLegIsRight());
        Assertions.assertEquals(footballer.getOverallRating(), footballerDTO.getOverallRating());
        Assertions.assertEquals(footballer.getFootballTeam().getId(), footballerDTO.getFootballTeamId());
    }

    @Test
    void footballerDTOToFootballerWithoutFootballerTeamId() {
        FootballerDTO footballerDTO = new FootballerDTO();
        footballerDTO.setSurname("Messi");
        footballerDTO.setAge(33);
        footballerDTO.setWorkingLegIsRight(false);
        footballerDTO.setOverallRating(96);

        Footballer footballer = footballerMapper.footballerDTOToFootballer(footballerDTO);

        Assertions.assertEquals(footballerDTO.getSurname(), footballer.getSurname());
        Assertions.assertEquals(footballerDTO.getAge(), footballer.getAge());
        Assertions.assertEquals(footballerDTO.getWorkingLegIsRight(), footballer.getWorkingLegIsRight());
        Assertions.assertEquals(footballerDTO.getOverallRating(), footballer.getOverallRating());
    }
}
