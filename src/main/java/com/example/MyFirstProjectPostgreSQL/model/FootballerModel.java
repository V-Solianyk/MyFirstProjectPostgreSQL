package com.example.MyFirstProjectPostgreSQL.model;

import com.example.MyFirstProjectPostgreSQL.entity.FootballTeam;
import lombok.Data;

import javax.persistence.ManyToOne;

@Data
public class FootballerModel {
    String surname;

    Integer age;

    Integer overallRating;

    String workingLeg;

    @ManyToOne
    private FootballTeam footballTeam;
}
