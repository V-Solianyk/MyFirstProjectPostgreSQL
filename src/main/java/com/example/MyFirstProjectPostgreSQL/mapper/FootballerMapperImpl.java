package com.example.MyFirstProjectPostgreSQL.mapper;

import com.example.MyFirstProjectPostgreSQL.entity.Footballer;
import com.example.MyFirstProjectPostgreSQL.model.FootballerModel;
import org.springframework.stereotype.Component;

@Component
public class FootballerMapperImpl implements FootballerMapper {
    @Override
    public FootballerModel footballerToFootballerModel(Footballer footballer) {
        FootballerModel footballerModel = new FootballerModel();
        footballerModel.setAge(footballer.getAge());
        footballerModel.setSurname(footballer.getSurname());
        footballerModel.setOverallRating(footballer.getOverallRating());
        footballerModel.setWorkingLeg(footballer.getWorkingLeg());
        footballerModel.setFootballTeam(footballer.getFootballTeam());

        return footballerModel;
    }

    @Override
    public Footballer footballerModelToFootballer(FootballerModel footballerModel) {
        Footballer footballer = new Footballer();
        footballer.setAge(footballerModel.getAge());
        footballer.setSurname(footballerModel.getSurname());
        footballer.setOverallRating(footballerModel.getOverallRating());
        footballer.setWorkingLeg(footballerModel.getWorkingLeg());
        footballer.setFootballTeam(footballerModel.getFootballTeam());

        return footballer;
    }
}
