package com.example.MyFirstProjectPostgreSQL.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Data
@Entity
public class FootballTeam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotBlank(message = "Team name can not be null or blank.")
    String teamName;

    @Min(1500)
    Integer yearOfFoundation;

    @Min(0)
    Long teamBudget;

    @OneToMany(mappedBy="footballTeam")
   private Set<Footballer> footballers;

}
