package com.example.MyFirstProjectPostgreSQL.entity;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@Entity
public class Footballer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Footballer surname can not be null or blank.")
    String surname;

    @Min(16)
    @Max(40)
    Integer age;

    @Min(45)
    @Max(99)
    Integer overallRating;

    @Value("${some.key:right}")
    String workingLeg;

    @ManyToOne
    private FootballTeam footballTeam;
}
