package com.example.MyFirstProjectPostgreSQL.dto;

import lombok.Data;

@Data
public class FootballerDTO {
    private String surname;

    private Integer age;

    private Integer overallRating;

    private Boolean workingLegIsRight = true;

    private Long footballTeamId;
}
