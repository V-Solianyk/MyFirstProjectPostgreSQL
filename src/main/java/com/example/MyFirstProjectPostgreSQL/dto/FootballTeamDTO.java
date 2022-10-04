package com.example.MyFirstProjectPostgreSQL.dto;

import com.example.MyFirstProjectPostgreSQL.entity.Footballer;
import lombok.Data;

import java.util.Set;

@Data
public class FootballTeamDTO {
    private String teamName;

    private Integer yearOfFoundation;

    private Long teamBudget;

    private Set<Footballer> footballers;

}
