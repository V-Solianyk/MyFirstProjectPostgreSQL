package com.example.MyFirstProjectPostgreSQL.mapper.footballer;

import com.example.MyFirstProjectPostgreSQL.entity.Footballer;
import com.example.MyFirstProjectPostgreSQL.dto.FootballerDTO;
import org.springframework.stereotype.Component;

@Component
public class FootballerMapperImpl implements FootballerMapper {
    @Override
    public FootballerDTO footballerToFootballerDTO(Footballer footballer) {
        FootballerDTO footballerDTO = new FootballerDTO();
        footballerDTO.setAge(footballer.getAge());
        footballerDTO.setSurname(footballer.getSurname());
        footballerDTO.setOverallRating(footballer.getOverallRating());
        footballerDTO.setWorkingLegIsRight((footballer.getWorkingLegIsRight()));
        if (footballer.getFootballTeam() != null) {
            footballerDTO.setFootballTeamId(footballer.getFootballTeam().getId());
        }

        return footballerDTO;
    }

    @Override
    public Footballer footballerDTOToFootballer(FootballerDTO footballerDTO) {
        Footballer footballer = new Footballer();
        footballer.setAge(footballerDTO.getAge());
        footballer.setSurname(footballerDTO.getSurname());
        footballer.setOverallRating(footballerDTO.getOverallRating());
        footballer.setWorkingLegIsRight(footballerDTO.getWorkingLegIsRight());

        return footballer;
    }
}
