package com.example.MyFirstProjectPostgreSQL.dto;

import com.example.MyFirstProjectPostgreSQL.entity.FootballTeam;
import lombok.Data;

@Data
public class FootballerDTO {
    private String surname;

    private Integer age;

    private Integer overallRating;

    private String workingLeg;

    private FootballTeam footballTeam;
}
