package com.example.MyFirstProjectPostgreSQL.dto;

import lombok.Data;

import java.util.Set;

@Data
public class FootballTeamDTO {
    private String teamName;

    private Integer yearOfFoundation;

    private Long teamBudget;

    private Set<Long> footballers_id;

}
