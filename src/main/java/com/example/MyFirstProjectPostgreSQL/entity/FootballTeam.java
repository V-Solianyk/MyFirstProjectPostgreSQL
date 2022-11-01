package com.example.MyFirstProjectPostgreSQL.entity;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
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

    @NotBlank(message = "The team's name can not be null or blank.")
    private String teamName;

    @Min(1500)
    private int yearOfFoundation;

    @OneToOne(
            mappedBy = "footballTeam",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private TeamBudget teamBudget;

    @OneToMany(
            mappedBy = "footballTeam",
            cascade = CascadeType.ALL)
    private Set<Footballer> footballers = new HashSet<>();

    @Override
    public String toString() {
        return "FootballTeam{" +
                "id=" + id +
                ", teamName='" + teamName + '\'' +
                ", yearOfFoundation=" + yearOfFoundation +
                '}';
    }
}

