package com.example.MyFirstProjectPostgreSQL.dto;

import lombok.Data;

@Data
public class FootballTeamDTO {
    private String teamName;

    private Integer yearOfFoundation;

    private Long teamBudget;
}
