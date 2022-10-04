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
        footballerDTO.setWorkingLeg(footballer.getWorkingLeg());
        footballerDTO.setFootballTeam(footballer.getFootballTeam());

        return footballerDTO;
    }

    @Override
    public Footballer footballerDTOToFootballer(FootballerDTO footballerDTO) {
        Footballer footballer = new Footballer();
        footballer.setAge(footballerDTO.getAge());
        footballer.setSurname(footballerDTO.getSurname());
        footballer.setOverallRating(footballerDTO.getOverallRating());
        footballer.setWorkingLeg(footballerDTO.getWorkingLeg());
        footballer.setFootballTeam(footballerDTO.getFootballTeam());

        return footballer;
    }
}
