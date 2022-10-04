package com.example.MyFirstProjectPostgreSQL.entity;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class FootballTeam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Team name can not be null or blank.")
    private String teamName;

    @Min(1500)
    private Integer yearOfFoundation;

    @Min(0)
    private Long teamBudget;

    @OneToMany(
            mappedBy = "footballTeam",
            cascade = CascadeType.ALL)
    private Set<Footballer> footballers = new HashSet<>();

}
