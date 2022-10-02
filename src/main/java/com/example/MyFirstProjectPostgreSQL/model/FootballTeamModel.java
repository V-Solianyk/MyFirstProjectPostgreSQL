package com.example.MyFirstProjectPostgreSQL.model;

import com.example.MyFirstProjectPostgreSQL.entity.Footballer;
import lombok.Data;

import javax.persistence.OneToMany;
import java.util.Set;

@Data
public class FootballTeamModel {
    String teamName;

    Integer yearOfFoundation;

    Long teamBudget;

    @OneToMany(mappedBy="footballTeam")
    private Set<Footballer> footballers;

}
