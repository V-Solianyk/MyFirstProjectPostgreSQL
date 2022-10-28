package com.example.MyFirstProjectPostgreSQL.dto;

import lombok.Data;

@Data
public class FootballerDTO {
    private String surname;

    private int age;

    private int overallRating;

    private Boolean workingLegIsRight = true;

    private Long footballTeamId;
}
